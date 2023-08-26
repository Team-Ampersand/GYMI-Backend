package com.example.gymi.domain.notice.entity

import com.example.gymi.domain.user.entity.User
import com.example.gymi.global.entity.BaseTimeEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "notice")
class Notice(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    val id: Long = 0,

    @Column(name = "notice_title", nullable = false)
    var title: String,

    @Column(name = "notice_content", nullable = false)
    var content: String,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notice")
    val noticeFile: List<NoticeFile>,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,
) : BaseTimeEntity() {
    fun editNotice(title: String, content: String) {
        this.title = title
        this.content = content
        this.modifiedDate = LocalDateTime.now()
    }
}