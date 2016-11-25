package com.bakkenbaeck.sol.util;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;

import com.bakkenbaeck.sol.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CurrentCity {
    private final Context context;

    public CurrentCity(final Context context) {
        this.context = context;
    }

    public String get(final double lat, final double lng) {
        final Geocoder geocoder = new Geocoder(this.context, Locale.getDefault());
        try {
            final List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            final Address address = addresses.get(0);

            if (address.getSubAdminArea() != null) {
                return address.getSubAdminArea();
            }

            if (address.getLocality() != null) {
                return address.getLocality();
            }

            return defaultCity();
        } catch (final IOException e) {
            return defaultCity();
        }
    }

    @NonNull
    private String defaultCity() {
        return context.getString(R.string.default_city);
    }
}
