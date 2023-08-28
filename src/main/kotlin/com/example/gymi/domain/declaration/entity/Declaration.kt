package com.example.gymi.domain.declaration.entity

import com.example.gymi.domain.declaration.enums.DeclarationType
import com.example.gymi.domain.user.entity.User
import com.example.gymi.global.entity.BaseTimeEntity
import javax.persistence.*

@Entity
class Declaration(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Enumerated(EnumType.STRING)
    val type: DeclarationType,
    @Column(nullable = true)
    val content: String?,
    //TODO 추후에 자리나 예약 엔티티로 연관관계로 바꾸기
    val seatId: Long,
    @ManyToOne
    @JoinColumn(name = "reporter_id")
    val reporter: User
) : BaseTimeEntity()