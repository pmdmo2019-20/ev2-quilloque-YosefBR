package es.iessaladillo.pedrojoya.quilloque.room

data class RecentCalls(
     val callId: Long,
     val phoneNumber: String,
     val date: String,
     val type: Int,
     val time: String,
     val contactId: Long?,
     val contactName: String?)

data class SuggestedCall (
     val contactName: String,
     val phoneNumber: String
)
