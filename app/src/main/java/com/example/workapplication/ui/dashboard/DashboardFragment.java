package com.example.workapplication.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.map.MapController;
import com.example.workapplication.R;
import com.example.workapplication.databinding.FragmentDashboardBinding;



public class DashboardFragment extends Fragment {

    private MapView mMapView = null;
    private FragmentDashboardBinding binding;
    private BaiduMap mBaiduMap = null;
    private LocationClient mLocationClient = null;
    private TextView mtextView;
    // 是否是第一次定位
    private boolean isFirstLocate = true;
    // 当前定位模式
    private MyLocationConfiguration.LocationMode locationMode;

    private MapController mMapController = null;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {




        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mMapView=root.findViewById(R.id.bmapView);


        // 得到地图
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        //定位初始化
        mLocationClient = new LocationClient(root.getContext());

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(10000);
        // 可选，设置地址信息
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址描述
        option.setIsNeedLocationDescribe(true);

        //设置locationClientOption
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }


    // 继承抽象类BDAbstractListener并重写其onReceieveLocation方法来获取定位数据，并将其传给MapView
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }

            // 如果是第一次定位
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            if (isFirstLocate) {
                isFirstLocate = false;
                //给地图设置状态
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(ll));
            }

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);

            // ------------------  以下是可选部分 ------------------
            // 自定义地图样式，可选
            // 更换定位图标，这里的图片是放在 drawble 文件下的
            //BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.;
            // 定位模式 地图SDK支持三种定位模式：NORMAL（普通态）, FOLLOWING（跟随态）, COMPASS（罗盘态）
            locationMode = MyLocationConfiguration.LocationMode.NORMAL;
            // 定位模式、是否开启方向、设置自定义定位图标、精度圈填充颜色以及精度圈边框颜色5个属性（此处只设置了前三个）。
            MyLocationConfiguration mLocationConfiguration = new MyLocationConfiguration(locationMode, true, null);
            // 使自定义的配置生效
            mBaiduMap.setMyLocationConfiguration(mLocationConfiguration);
            // ------------------  可选部分结束 ------------------

            // 显示当前信息
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n经度：" + location.getLatitude());
            stringBuilder.append("\n纬度：" + location.getLongitude());
            stringBuilder.append("\n状态码：" + location.getLocType());
            stringBuilder.append("\n国家：" + location.getCountry());
            stringBuilder.append("\n城市：" + location.getCity());
            stringBuilder.append("\n区：" + location.getDistrict());
            stringBuilder.append("\n街道：" + location.getStreet());
            stringBuilder.append("\n地址：" + location.getAddrStr());
            Log.e("mapinfo",stringBuilder.toString());
            //mtextView.setText(stringBuilder.toString());
        }
    }
}
