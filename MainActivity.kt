package com.example.smartpill.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartpill.R
import com.example.smartpill.data.Pill
import com.example.smartpill.repo.PillRepository
import kotlinx.android.synthetic.main.activity_main.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var vm: PillViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this, PillViewModel.Factory(application)).get(PillViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PillAdapter()
        recyclerView.adapter = adapter

        vm.pills.observe(this, Observer { list ->
            adapter.submit(list)
        })

        addButton.setOnClickListener {
            startActivity(Intent(this, AddPillActivity::class.java))
        }
    }
}

class PillAdapter: RecyclerView.Adapter<PillAdapter.VH>() {
    private var items: List<Pill> = emptyList()
    fun submit(list: List<Pill>){ items = list; notifyDataSetChanged() }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_pill, parent, false)
        return VH(v)
    }
    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = items[position]
        holder.name.text = p.name
        holder.dose.text = p.dose + " at " + p.timeOfDay
        holder.stock.text = "Stock: ${'$'}{p.stock}  Expiry: ${'$'}{p.expiryIso}"
    }
    override fun getItemCount(): Int = items.size
    class VH(v: View): RecyclerView.ViewHolder(v){
        val name: TextView = v.findViewById(R.id.name)
        val dose: TextView = v.findViewById(R.id.dose)
        val stock: TextView = v.findViewById(R.id.stock)
    }
}
