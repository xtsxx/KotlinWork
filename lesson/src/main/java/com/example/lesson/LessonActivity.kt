package com.example.lesson

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.core.BaseView
import com.example.lesson.entity.Lesson

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter>, Toolbar.OnMenuItemClickListener {

    private lateinit var refreshLayout: SwipeRefreshLayout

    private val lessonAdapter = LessonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        findViewById<Toolbar>(R.id.toolbar).apply {
           inflateMenu(R.menu.menu_lesson)
           setOnMenuItemClickListener(this@LessonActivity)
        }
        findViewById<RecyclerView>(R.id.list).apply {
          layoutManager = LinearLayoutManager(this@LessonActivity)
          adapter = lessonAdapter
          addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayout.VERTICAL))
        }
        refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout).apply {
            setOnRefreshListener{ presenter.fetchData() }
            isRefreshing = true
        }
        presenter.fetchData()
    }

    fun showResult(lessons: List<Lesson>) {
        lessonAdapter.updateAndNotify(lessons)
        refreshLayout.isRefreshing = false
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        presenter.showPlayback()
        return false
    }

    override val presenter: LessonPresenter by lazy {
        LessonPresenter(this)
    }
}