package com.lautung.recyclerviewdemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private lateinit var mDatas: ArrayList<PersonInfo>
    private var personAdapter: PersonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recyclerView)
        initData()
        recyclerView?.layoutManager=LinearLayoutManager(this)
        personAdapter=PersonAdapter(this,mDatas)
        recyclerView?.adapter=personAdapter

    }

    private fun initData() {
        mDatas = arrayListOf<PersonInfo>()
        mDatas.add(PersonInfo(1, "姓名1"))
        mDatas.add(PersonInfo(2, "姓名2"))
        mDatas.add(PersonInfo(3, "姓名3"))
        mDatas.add(PersonInfo(4, "姓名4"))
        mDatas.add(PersonInfo(5, "姓名5"))
        mDatas.add(PersonInfo(6, "姓名6"))
        mDatas.add(PersonInfo(7, "姓名7"))
        mDatas.add(PersonInfo(8, "姓名8"))
        mDatas.add(PersonInfo(9, "姓名9"))
        mDatas.add(PersonInfo(10, "姓名10"))
        mDatas.add(PersonInfo(11, "姓名11"))
    }

    public fun ADD(view: View) {
//        var position = mDatas.size
//        val tempData = arrayListOf<PersonInfo>()
//
//        tempData.add( PersonInfo(12, "姓名12"));
//        tempData.add( PersonInfo(13, "姓名13"));
//        tempData.add( PersonInfo(14, "姓名114"));
//
//        mDatas.addAll(tempData)
//        personAdapter?.notifyItemRangeInserted(position, tempData.size)

        var newdata = arrayListOf<PersonInfo>()
        newdata.addAll(mDatas)
        newdata.add( PersonInfo(12, "姓名12"));
        newdata.add( PersonInfo(13, "姓名13"));
        newdata.add( PersonInfo(14, "姓名114"));
        personAdapter?.let {
            DiffUtil.calculateDiff(PersonAdapter.DiffUtilCallBack(mDatas,newdata),true).dispatchUpdatesTo(
                it
            )
            mDatas=newdata
            it.setDatas(newdata)
        }
    }

    public fun DELETE(view: View){
        mDatas.removeAt(1)
        personAdapter?.notifyItemRemoved(1)
    }

    public fun UPDATE(view: View){
        mDatas[1].name="姓名：我被更新了"
        personAdapter?.notifyItemChanged(1);
    }

    public fun UPDATE2(view: View){
        mDatas[1].name="姓名：我被更新了"
        val patload=Bundle()
        patload.putString("KEY_NAME", mDatas[1].name)
        personAdapter?.notifyItemChanged(1,patload)
    }




}