package com.capture.presentation.game;

import android.app.Activity;
import android.os.Bundle;

import com.capture.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

/**
 * Created by artem on 19.01.16.
 */
public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        MapView map = (MapView) findViewById(R.id.activityGame_MapView_map);
        map.setTileSource(TileSourceFactory.MAPNIK);
//        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(20);
        mapController.setCenter(new GeoPoint(54.318684, 48.403570));

        RotationGestureOverlay rotationGestureOverlay = new RotationGestureOverlay(this, map);
        rotationGestureOverlay.setEnabled(true);
        map.setMultiTouchControls(true);
        map.getOverlays().add(rotationGestureOverlay);

    }
}
