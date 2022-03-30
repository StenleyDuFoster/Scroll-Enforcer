package com.stenleone.scrollenforcer

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.enforceSingleScrollDirection() {
    val enforcer = SingleScrollDirectionRecyclerEnforcer()
    addOnItemTouchListener(enforcer)
    addOnScrollListener(enforcer)
}