package com.example.gymi.domain.notice.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "notice_file")
class NoticeFile(
    url: String,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id", nullable = false)
    val notice: Notice

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    val id: Long = 0

    @Column(name = "file_url")
    var url = url
        private set

    fun editNoticeFile(url: String) {
        this.url = url
    }
}