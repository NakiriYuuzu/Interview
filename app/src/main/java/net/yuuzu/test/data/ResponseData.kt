package net.yuuzu.test.data

data class ApiResponse(
    val code: Int,
    val message: String?,
    val data: Data?
)

data class Data(
    val totalCount: Int,
    var items: List<Item>
)


data class Item(
    var user: User,
    var tags: List<String>
)


data class User(
    var nickName: String,
    var imageUrl: String
)
