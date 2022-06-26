package com.yechaoa.materialdesign.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivityNotificationBinding

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 *
 * Created by yechao on 2022/6/22.
 * Describe :
 */
class NotificationActivity : ToolbarActivity<ActivityNotificationBinding>() {

    private val mNormalChannelId = "渠道id" // 唯一性
    private val mNormalChannelName = "渠道名称"

    private val mHighChannelId = "重要渠道id"
    private val mHighChannelName = "重要通知"

    private val mProgressChannelId = "进度条渠道id"
    private val mProgressChannelName = "进度条通知"

    private val mBigTextChannelId = "大文本渠道id"
    private val mBigTextChannelName = "大文本通知"

    private val mBigImageChannelId = "大图片渠道id"
    private val mBigImageChannelName = "大图片通知"

    private val mCustomChannelId = "自定义渠道id"
    private val mCustomChannelName = "自定义通知"

    private var mReceiver: NotificationReceiver? = null

    private lateinit var mBuilder: NotificationCompat.Builder

    companion object {
        private lateinit var mManager: NotificationManager
        private const val mNormalNotificationId = 9001 // 通知id
        private const val mHighNotificationId = 9002 // 通知id
        private const val mBigTextNotificationId = 9003 // 通知id
        private const val mProgressNotificationId = 9004 // 通知id
        private const val mBigImageNotificationId = 9005 // 通知id
        private const val mCustomNotificationId = 9006 // 通知id
        private const val mStopAction = "com.yechaoa.stop" // 暂停继续action
        private const val mDoneAction = "com.yechaoa.done" // 完成action
        private var mFlag = 0
        private var mIsStop = false // 是否在播放 默认未开始
    }

    override fun getViewBinding(): ActivityNotificationBinding {
        return ActivityNotificationBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.notification)
    }

    override fun initView() {
        mManager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        createReceiver()

        setClickListener()
    }

    private fun setClickListener() {
        mBinding.mbNormal.setOnClickListener {
            createNotificationForNormal()
        }

        mBinding.mbHigh.setOnClickListener {
            createNotificationForHigh()
        }

        mBinding.mbProgress.setOnClickListener {
            createNotificationForProgress()
        }

        mBinding.mbUpdateProgress.setOnClickListener {
            updateNotificationForProgress()
        }

        mBinding.mbBigText.setOnClickListener {
            createNotificationForBigText()
        }

        mBinding.mbBigImage.setOnClickListener {
            createNotificationForBigImage()
        }

        mBinding.mbCustom.setOnClickListener {
            createNotificationForCustom()
        }

        mBinding.mbUpdateCustom.setOnClickListener {
            updateNotificationForCustom()
        }
    }

    /**
     * 普通通知
     */
    private fun createNotificationForNormal() {
        // 适配8.0及以上 创建渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(mNormalChannelId, mNormalChannelName, NotificationManager.IMPORTANCE_LOW).apply {
                description = "描述"
                setShowBadge(false) // 是否在桌面显示角标
            }
            mManager.createNotificationChannel(channel)
        }
        // 点击意图 // setDeleteIntent 移除意图
        val intent = Intent(this, MaterialButtonActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        // 构建配置
        mBuilder = NotificationCompat.Builder(this@NotificationActivity, mNormalChannelId)
            .setContentTitle("普通通知") // 标题
            .setContentText("普通通知内容") // 文本
            .setSmallIcon(R.mipmap.ic_launcher) // 小图标
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_avatar)) // 大图标
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) // 7.0 设置优先级
            .setContentIntent(pendingIntent) // 跳转配置
            .setAutoCancel(true) // 是否自动消失（点击）or mManager.cancel(mNormalNotificationId)、cancelAll、setTimeoutAfter()
        // 发起通知
        mManager.notify(mNormalNotificationId, mBuilder.build())
    }

    /**
     * 重要通知
     */
    private fun createNotificationForHigh() {
        val intent = Intent(this, MaterialButtonActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(mHighChannelId, mHighChannelName, NotificationManager.IMPORTANCE_HIGH)
            channel.setShowBadge(true)
            mManager.createNotificationChannel(channel)
        }
        mBuilder = NotificationCompat.Builder(this@NotificationActivity, mHighChannelId)
            .setContentTitle("重要通知")
            .setContentText("重要通知内容")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_avatar))
            .setAutoCancel(true)
            .setNumber(999) // 自定义桌面通知数量
            .addAction(R.mipmap.ic_avatar, "去看看", pendingIntent)// 通知上的操作
            .setCategory(NotificationCompat.CATEGORY_MESSAGE) // 通知类别，"勿扰模式"时系统会决定要不要显示你的通知
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE) // 屏幕可见性，锁屏时，显示icon和标题，内容隐藏
        mManager.notify(mHighNotificationId, mBuilder.build())
    }

    /**
     * 进度条通知
     */
    private fun createNotificationForProgress() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(mProgressChannelId, mProgressChannelName, NotificationManager.IMPORTANCE_DEFAULT)
            mManager.createNotificationChannel(channel)
        }
        val progressMax = 100
        val progressCurrent = 30
        mBuilder = NotificationCompat.Builder(this@NotificationActivity, mProgressChannelId)
            .setContentTitle("进度通知")
            .setContentText("下载中：$progressCurrent%")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_avatar))
            // 第3个参数indeterminate，false表示确定的进度，比如100，true表示不确定的进度，会一直显示进度动画，直到更新状态下载完成，或删除通知
            .setProgress(progressMax, progressCurrent, false)

        mManager.notify(mProgressNotificationId, mBuilder.build())
    }

    /**
     * 更新进度条通知
     * 1.更新进度：修改进度值即可
     * 2.下载完成：总进度与当前进度都设置为0即可，同时更新文案
     */
    private fun updateNotificationForProgress() {
        if (::mBuilder.isInitialized) {
            val progressMax = 100
            val progressCurrent = 50
            // 1.更新进度
            mBuilder.setContentText("下载中：$progressCurrent%").setProgress(progressMax, progressCurrent, false)
            // 2.下载完成
            //mBuilder.setContentText("下载完成！").setProgress(0, 0, false)
            mManager.notify(mProgressNotificationId, mBuilder.build())
            Toast.makeText(this, "已更新进度到$progressCurrent%", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "请先发一条进度条通知", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 大文本通知
     */
    private fun createNotificationForBigText() {
        val bigText =
            "A notification is a message that Android displays outside your app's UI to provide the user with reminders, communication from other people, or other timely information from your app. Users can tap the notification to open your app or take an action directly from the notification."
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(mBigTextChannelId, mBigTextChannelName, NotificationManager.IMPORTANCE_DEFAULT)
            mManager.createNotificationChannel(channel)
        }
        mBuilder = NotificationCompat.Builder(this@NotificationActivity, mBigTextChannelId)
            .setContentTitle("大文本通知")
            .setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_avatar))
            .setAutoCancel(true)
        mManager.notify(mBigTextNotificationId, mBuilder.build())
    }

    /**
     * 大图片通知
     */
    private fun createNotificationForBigImage() {
        val bigPic = BitmapFactory.decodeResource(resources, R.drawable.ic_big_pic)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(mBigImageChannelId, mBigImageChannelName, NotificationManager.IMPORTANCE_DEFAULT)
            mManager.createNotificationChannel(channel)
        }
        mBuilder = NotificationCompat.Builder(this@NotificationActivity, mBigImageChannelId)
            .setContentTitle("大图片通知")
            .setContentText("有美女，展开看看")
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bigPic))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_avatar))
            .setAutoCancel(true)
        mManager.notify(mBigImageNotificationId, mBuilder.build())
    }

    /**
     * 自定义通知
     */
    private fun createNotificationForCustom() {
        // 适配8.0及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(mCustomChannelId, mCustomChannelName, NotificationManager.IMPORTANCE_DEFAULT)
            mManager.createNotificationChannel(channel)
        }

        // 适配12.0及以上
        mFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        // 添加自定义通知view
        val views = RemoteViews(packageName, R.layout.layout_notification)

        // 添加暂停继续事件
        val intentStop = Intent(mStopAction)
        val pendingIntentStop = PendingIntent.getBroadcast(this@NotificationActivity, 0, intentStop, mFlag)
        views.setOnClickPendingIntent(R.id.btn_stop, pendingIntentStop)

        // 添加完成事件
        val intentDone = Intent(mDoneAction)
        val pendingIntentDone = PendingIntent.getBroadcast(this@NotificationActivity, 0, intentDone, mFlag)
        views.setOnClickPendingIntent(R.id.btn_done, pendingIntentDone)

        // 创建Builder
        mBuilder = NotificationCompat.Builder(this@NotificationActivity, mCustomChannelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_avatar))
            .setAutoCancel(true)
            .setCustomContentView(views)
            .setCustomBigContentView(views)// 设置自定义通知view

        // 发起通知
        mManager.notify(mCustomNotificationId, mBuilder.build())
    }

    /**
     * 创建广播接收器
     */
    private fun createReceiver() {
        val intentFilter = IntentFilter()
        // 添加接收事件监听
        intentFilter.addAction(mStopAction)
        intentFilter.addAction(mDoneAction)
        mReceiver = NotificationReceiver()
        // 注册广播
        registerReceiver(mReceiver, intentFilter)
    }

    private class NotificationReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // 拦截接收事件
            if (intent.action == mStopAction) {
                // 改变状态
                mIsStop = !mIsStop
                val activity = context as? NotificationActivity
                activity?.updateCustomView()
            } else if (intent.action == mDoneAction) {
                Toast.makeText(context, "完成", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * 更新自定义通知
     */
    private fun updateNotificationForCustom() {
        // 发送通知 更新状态及UI
        sendBroadcast(Intent(mStopAction))
    }

    /**
     * 更新自定义通知View
     */
    private fun updateCustomView() {
        val views = RemoteViews(packageName, R.layout.layout_notification)
        val intentUpdate = Intent(mStopAction)
        val pendingIntentUpdate = PendingIntent.getBroadcast(this, 0, intentUpdate, mFlag)
        views.setOnClickPendingIntent(R.id.btn_stop, pendingIntentUpdate)
        // 根据状态更新UI
        if (mIsStop) {
            views.setTextViewText(R.id.tv_status, "那些你很冒险的梦-停止播放")
            views.setTextViewText(R.id.btn_stop, "继续")
            mBinding.mbUpdateCustom.text = "继续"
        } else {
            views.setTextViewText(R.id.tv_status, "那些你很冒险的梦-正在播放")
            views.setTextViewText(R.id.btn_stop, "暂停")
            mBinding.mbUpdateCustom.text = "暂停"
        }

        mBuilder.setCustomContentView(views).setCustomBigContentView(views)
        // 重新发起通知更新UI，注意：必须得是同一个通知id，即mCustomNotificationId
        mManager.notify(mCustomNotificationId, mBuilder.build())
    }

    override fun onDestroy() {
        super.onDestroy()
        // 取消注册
        mReceiver?.let { unregisterReceiver(it) }
    }
}