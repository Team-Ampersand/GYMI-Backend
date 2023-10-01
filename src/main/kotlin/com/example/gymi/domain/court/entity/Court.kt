package com.example.gymi.domain.court.entity

import com.example.gymi.domain.court.enum.CourtNumber
import com.example.gymi.domain.court.enum.DayPeriod
import com.example.gymi.domain.court.enum.Week
import com.example.gymi.domain.reservation.entity.Reservation
import javax.persistence.*

@Entity
class Court(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(length = 30)
    val name: String,

    var count: Int = 0,

    val maxCount: Int,

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    val courtNumber: CourtNumber, // 코트 번호

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    val week: Week, // 월, 화, 수, 목, 금

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    val dayPeriod: DayPeriod, // 아침 , 점심, 저녁

    @OneToMany(mappedBy = "court", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    val reservations: List<Reservation> = arrayListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    val activity: Activity // (배구, 농구, 배드민턴 등)
) {

    fun addCount() {
        synchronized(this) {
            count += 1
        }
    }

    fun removeCount() {
        synchronized(this) {
            count -= 1
        }
    }
}