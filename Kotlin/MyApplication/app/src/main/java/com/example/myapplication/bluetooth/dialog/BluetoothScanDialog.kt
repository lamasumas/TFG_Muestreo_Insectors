package com.example.myapplication.bluetooth.dialog

import android.app.Dialog
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.bluetooth.BleController
import com.example.myapplication.bluetooth.BluetoothManager
import com.polidea.rxandroidble2.RxBleClient
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class BluetoothScanDialog(context:Context): Dialog(context) {

    private var disposable:Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bluetoothAdapter= BluetoothRvAdapter(this)
        setCanceledOnTouchOutside(true);
        val bleController = BleController()
        val checkBluetooth = BluetoothAdapter.getDefaultAdapter()
        val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager;
        if(checkBluetooth.isEnabled  && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            setContentView(R.layout.bluetooth_dialog)
            val recyclerView = this.findViewById<RecyclerView>(R.id.rvBluetoothDevices).apply {
                setHasFixedSize(false)
                layoutManager = LinearLayoutManager(this.context)
                adapter = bluetoothAdapter
                visibility = View.VISIBLE
                disposable = bleController.scanDevices(context).subscribe {
                    bluetoothAdapter.addScanResult(it)
                }
            }
        }else {
            setContentView(R.layout.bluetooth_alternative_dialog)
            if(!checkBluetooth.isEnabled)
                findViewById<TextView>(R.id.tvErrorBluetooth).text = context.getText(R.string.tvBluetoothDesactivado)
            if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                findViewById<TextView>(R.id.tvErrorBluetooth).text = context.getText(R.string.tvBluetoothLocalizacionApagado)
        }


    }

    override fun dismiss() {
        disposable?.dispose()
        super.dismiss()
    }

}