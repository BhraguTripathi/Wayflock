package com.example.wayflock.domain.model

data class TripInvite (
    val id: String,                         // Unique invite ID (Firestore document ID)
    val tripId: String,                     // The trip being invited to
    val tripName: String,                   // Display name of the trip
    val tripImageUrl: String? = null,       // Optional cover image of the trip
    val tripLocation: String? = null,       // Destination of the trip
    val invitedByUserId: String,            // UID of the person who sent the invite
    val invitedByName: String,              // Display name of the inviter ("Arjun Sharma")
    val inviterPhotoUrl: String? = null,    // Profile photo of the inviter
    val sentAt: Long = 0L,                  // Epoch timestamp when invite was sent
    val status: InviteStatus = InviteStatus.PENDING,
)

enum class InviteStatus {
    PENDING,
    ACCEPTED,
    DECLINED,
    EXPIRED,
}