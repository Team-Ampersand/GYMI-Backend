package com.example.gymi.domain.user.entity

import com.example.gymi.domain.user.enums.Role
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@DynamicUpdate
class User(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID,

    @Column(length = 20)
    val email: String,

    @Column(length = 20)
    val nickname: String,

    val grade: Int,

    val classNum: Int,

    val number: Int,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = [JoinColumn(name = "user_id")])
    var roles: MutableList<Role> = mutableListOf()
)