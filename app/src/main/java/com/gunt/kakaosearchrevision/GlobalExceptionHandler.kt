package com.gunt.kakaosearchrevision

import android.app.Activity
import android.content.Intent
import android.os.Process
import com.gunt.kakaosearchrevision.ui.ErrorActivity
import kotlin.system.exitProcess

/* 단일액티비티앱에서 활용 가능
*  여러 액티비티 사용 시 최상단의 액티비티 context 필요 */
object GlobalExceptionHandler : Thread.UncaughtExceptionHandler {

    private var activity: Activity? = null

    fun setActivity(activity: Activity){
        this.activity = activity
    }

    /* 에러 발생 시 일시적 오류화면으로 전달
    * QA 시 빠르게 버그 리포팅 가능
    * release 버전에서 오류화면으로 사용자 이탈 방지할 수 있음 */
    override fun uncaughtException(t: Thread, e: Throwable) {
        activity?.run {
            e.printStackTrace()
            startErrorActivity(this, e.toString())
        } ?: Thread.getDefaultUncaughtExceptionHandler()?.uncaughtException(t, e)


        //프로세스를 종료시켜 앱종료에 대한 시스템 다이얼로그 팝업 종료 -> 사용자에게 예상치 못한 종료를 안드로이드 기본 다이얼로그가 아닌 커스텀한 화면으로 보여주려 함
        Process.killProcess(Process.myPid())
        exitProcess(-1)
    }

    private fun startErrorActivity(activity: Activity, errorText: String) = activity.run {
        startActivity(
            Intent(this, ErrorActivity::class.java).apply {
                putExtra(ErrorActivity.EXTRA_ERROR_TEXT, errorText)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
        )
        finish()
    }
}
