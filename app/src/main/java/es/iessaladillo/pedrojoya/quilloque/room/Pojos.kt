package es.iessaladillo.pedrojoya.quilloque.room

data class RecentCalls(
    private val callId: Long,
    private val phoneNumber: String,
    private val date: String,
    private val type: String,
    private val time: String,
    private val contactId: Long,
    private val contactName: String)

data class SuggestedCall (
    private val contactName: String,
    private val phoneNumber: String
)
