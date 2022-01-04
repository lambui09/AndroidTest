package com.android.pixelteam.buiduclamtest.utils.dialogManager

import android.content.Context
import java.lang.ref.WeakReference

class DialogManagerImpl(ctx: Context?) : DialogManager {
    private var context: WeakReference<Context?>? = null
    private var progressDialog: ProgressDialog? = null

    init {
        context = WeakReference(ctx).apply {
            progressDialog = ctx?.let { ProgressDialog(it) }
        }
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun hideLoading() {
        progressDialog?.dismiss()
    }
}