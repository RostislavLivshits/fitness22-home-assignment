package com.mymasterapps.fitness22homeasignment.ui.util

import android.net.Uri

fun asset(path: String): String =
    "file:///android_asset/" + Uri.encode(path, "/")