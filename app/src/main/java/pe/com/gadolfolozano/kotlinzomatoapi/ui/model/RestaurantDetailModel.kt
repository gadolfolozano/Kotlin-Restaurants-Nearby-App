package pe.com.gadolfolozano.kotlinzomatoapi.ui.model

import android.os.Parcel
import android.os.Parcelable

class RestaurantDetailModel(
    val id: String?,
    val name: String?,
    val address: String?,
    val cuisines: String?,
    val highlights: List<String>?,
    val photos: List<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeString(cuisines)
        parcel.writeStringList(highlights)
        parcel.writeStringList(photos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RestaurantDetailModel> {
        override fun createFromParcel(parcel: Parcel): RestaurantDetailModel {
            return RestaurantDetailModel(parcel)
        }

        override fun newArray(size: Int): Array<RestaurantDetailModel?> {
            return arrayOfNulls(size)
        }
    }
}
