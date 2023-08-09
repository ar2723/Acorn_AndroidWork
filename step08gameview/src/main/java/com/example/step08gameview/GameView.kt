package com.example.step08gameview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.Random


class GameView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?=null
) : View(context, attrs) {

    lateinit var handler2:Handler
    //사용할 필드를 미리 만들기
    lateinit var backImg:Bitmap
    //화면의 폭과 높이를 저장할 필드
    private var width = 0
    private var height = 0
    //배경이미지의 y 좌표
    var back1Y = 0
    var back2Y = 0
    //유닛의 크기
    var unitSize = 0
    //드래곤의 자표
    var dragonX = 0
    var dragonY = 0
    // null로 초기화 된 Bitmap 객체를 담을 수 있는 방 4개짜리 배열 객체 생성
    var drangonImgs = arrayOfNulls<Bitmap>(4)
    // 드래곤 이미지를 그릴 때 사용할 Index 값
    var drangonIndex = 0
    // 카운트를 셀 필드를 만들고 초기값 0 대입
    var count = 0
    // 미사일 이미지를 저장할 배열
    val missImgs = arrayOfNulls<Bitmap>(3)
    // 미사일의 크기
    var missSize = 0
    // 미사일 객체를 담을 List
    val missList = mutableListOf<Missile>()
    // 미사일의 속도
    var missSpeed = 0
    // 배열안에 배열(2차원 배열)
    var enemyImgs = Array(2){ arrayOfNulls<Bitmap>(2) }
    // 적기 객체를 저장할 List
    var enemyList = mutableListOf<Enemy>()
    // 적기의 x 좌표 5개를 저장할 정수 배열
    var enemyX = IntArray(5)
    // 적기를 우연히 만들기 위한 Random 객체
    var ran = Random()
    // 적기가 만들어 진 이후 count를 셀 필드 (적기가 겹쳐서 만들어지지 않게 하기 위해)
    var postCount = 0
    //SoundManager 객체의 참족밧을 저장할 필드
    var sManager:SoundManager? = null

    //게임 화면을 준비하는 함수
    fun init() {
        //사용할 이미지를 미리 로딩해서 참조값을 필드에 저장하기
        //원본 이미지
        var backImg:Bitmap = BitmapFactory.decodeResource(resources, R.drawable.backbg)
        //원본 이미지를 원하는 크기로 변경해서 필드에 저장하기
        this.backImg = Bitmap.createScaledBitmap(backImg, width, height, false)

        //드래곤의 이미지를 로딩 후 사이즈 조절
        var dragonImg1:Bitmap = BitmapFactory.decodeResource(resources, R.drawable.unit1)
        drangonImgs[0] = Bitmap.createScaledBitmap(dragonImg1, unitSize, unitSize, false)
        var dragonImg2:Bitmap = BitmapFactory.decodeResource(resources, R.drawable.unit2)
        drangonImgs[1] = Bitmap.createScaledBitmap(dragonImg2, unitSize, unitSize, false)
        var dragonImg3:Bitmap = BitmapFactory.decodeResource(resources, R.drawable.unit3)
        drangonImgs[2] = Bitmap.createScaledBitmap(dragonImg3, unitSize, unitSize, false)
        //3번 index에는 2번째 dragon 이미지를 넣어둔다.
        drangonImgs[3] = drangonImgs[1]

        // 미사일 이미지 로딩
        val missImg1 = BitmapFactory.decodeResource(resources, R.drawable.mi1)
        val missImg2 = BitmapFactory.decodeResource(resources, R.drawable.mi2)
        val missImg3 = BitmapFactory.decodeResource(resources, R.drawable.mi3)
        // 미사일 이미지 크기 조절
        missImgs[0] = Bitmap.createScaledBitmap(missImg1, missSize, missSize, false)
        missImgs[1] = Bitmap.createScaledBitmap(missImg2, missSize, missSize, false)
        missImgs[2] = Bitmap.createScaledBitmap(missImg3, missSize, missSize, false)

        // 적기 이미지 로딩
        val enemyImg1 = BitmapFactory.decodeResource(resources, R.drawable.silver1)
        val enemyImg2 = BitmapFactory.decodeResource(resources, R.drawable.silver2)
        val enemyImg3 = BitmapFactory.decodeResource(resources, R.drawable.gold1)
        val enemyImg4 = BitmapFactory.decodeResource(resources, R.drawable.gold2)
        // 적기 이미지 사이즈 조절
        enemyImgs[0][0] = Bitmap.createScaledBitmap(enemyImg1, unitSize, unitSize, false)
        enemyImgs[0][1] = Bitmap.createScaledBitmap(enemyImg2, unitSize, unitSize, false)
        enemyImgs[1][0] = Bitmap.createScaledBitmap(enemyImg3, unitSize, unitSize, false)
        enemyImgs[1][1] = Bitmap.createScaledBitmap(enemyImg4, unitSize, unitSize, false)
        // 적기의 x 좌표를 구해서 배열에 저장한다.
        for(i in 0..4){
            enemyX[i] = unitSize/2 + unitSize*i
        }


        //Handeler 객체를 init 블럭에서 생성해서 참조값을 필드에 넣어둔다.
        this.handler2=object:Handler() {
            //Handler 객체에 메세지가 도착하면 호출되는 메소드
            override fun handleMessage(msg: Message) {
                //화면 갱신하기
                invalidate()
                // handler 객체에 빈 메시지를 10/1000 초 이후에 다시 보내기
                sendEmptyMessageDelayed(0, 10)
            }
        }
        //Handler 객체에 빈메세지를 한번 보내기
        handler2.sendEmptyMessageDelayed(0, 20)
    }

    //onDraw() 메소드에서 화면 구성하기
    override fun onDraw(canvas: Canvas?) {
        //배경이미지, 유닛 아미지, 적군이미지 등등 그리기
        canvas?.drawBitmap(this.backImg, 0f, back1Y.toFloat(), null)
        canvas?.drawBitmap(this.backImg, 0f, back2Y.toFloat(), null)

        //반복문 돌면서 미사일 그리기
        for(tmp in missList){
            missImgs[0]?.let {
                canvas?.drawBitmap(it,
                    tmp.x-missSize/2.toFloat(),
                    tmp.y-missSize/2.toFloat(),
                    null
                )
            }

        }

        //드래곤 이미지 그리기
        drangonImgs[drangonIndex]?.let {
            canvas?.drawBitmap(it,
                (dragonX-unitSize/2).toFloat(),
                (dragonY-unitSize/2).toFloat(),
                null)
        }

        //적기 그리기
        enemyList.forEach {
            // it 즉 Enemy 객체의 참조값을 tmp에 대입해서 사용
            var tmp = it
            if(tmp.isFall){ //만일 현재 추락 중이라면
                enemyImgs[tmp.type][tmp.imageIndex]?.let {
                    //canvas의 정상 상태를 ( 변화를 가하지 않은 상태 ) 저장한다.
                    canvas?.save()
                    //canvas의 좌표계를 적기의 위치로 평행이동
                    canvas?.translate(tmp.x.toFloat(), tmp.y.toFloat())
                    //canvas의 좌표계를 적기의 회전량 만큼 회전
                    canvas?.rotate(tmp.angle.toFloat())
                    //적기를 원점(0, 0)에 그리고
                    val scaled = Bitmap.createScaledBitmap(it, tmp.size, tmp.size, false)
                    canvas?.drawBitmap(scaled,
                        (0 - unitSize/2).toFloat(),
                        (0 - unitSize/2).toFloat(),
                        null)
                    //저장했던 정상상태로 되돌린다.
                    canvas?.restore()
                }
            }else {
                enemyImgs[tmp.type][tmp.imageIndex]?.let {
                    canvas?.drawBitmap(it,
                        (tmp.x - unitSize/2).toFloat(),
                        (tmp.y - unitSize/2).toFloat(),
                        null
                    )
                }
            }
        }

        //카운트 값을 올린다
        count++
        //만일 conunt를 10으로 나눈 나머지 값이 0이라면
        if(count%10 == 0){
            //드래곤 애니메이션 관련 처리
            drangonIndex ++
            if(drangonIndex==4){ // 만일 존재하지 않는 인덱스라면
                drangonIndex = 0  // 다시 처음으로 되돌리기
            }
        }


        //배경 이미지 관련처리
        back1Y += 5
        back2Y += 5
        //만일 배경1이 좌표가 아래로 벗어 난다면
        if(back1Y >= height){
            //배경1을 상단으로 다시 보낸다.
            back1Y = -height
            //배경2와 오차가 생기지 않게 하기위해 복원하기
            back2Y = 0
        }
        if(back2Y >= height){
            //배경2을 상단으로 다시 보낸다.
            back2Y = -height
            //배경1과 오차가 생기지 않게 하기위해 복원하기
            back1Y = 0
        }
        //미사일 관련 처리를 하는 함수 호출
        missileService()
        // 적기 관련 처리를 하는 함수 호출
        enemyService()
        // 적기와 미사일 충돌을 검사하는 함수 호출
        checkStrike()
    }

    //적기와 미사일 충돌 검사
    fun checkStrike(){
        for(i in missList.indices){
            //i번째 미사일 객체
            val m = missList.get(i)
            for(j in enemyList.indices){
                // j번째 적기 객체
                val e = enemyList.get(j)
                // i번째 미사일이 j번째 적기의 4각형 영역 안에 있는지 여부
                val isStrike = m.x > e.x - unitSize/2 &&
                               m.x < e.x + unitSize/2 &&
                               m.y > e.y - unitSize/2 &&
                               m.y < e.y + unitSize/2

                if(isStrike && !e.isFall){ // 현재 추락중인 적기는 제외한다.
                    //효과음 재생
                    sManager?.playSound(GameActivity.SOUND_SHOOT)
                    //적기 에너지를 줄이고
                    e.energy -= 20
                    //미사일을 제거하고
                    m.isDead = true
                    //만일 적기의 에너지가 0이하라면 적기가 추락하도록 한다.
                    if(e.energy <= 0){
                        //바로 제거 되는 대신 적기가 추락 상태가 되도록 한다.
                        e.isFall = true
                    }
                }
            }
        }
    }


    // 적기 관련 처리를 하는 함수
    fun enemyService(){
        if(count%10 == 0){
            //반복문 돌면서
            for(tmp in enemyList){
                // 모든 적기의 이미지 인덱스를 1증가시킨다
                tmp.imageIndex++
                // 만일 존재하지 않는 인덱스라면
                if(tmp.imageIndex == 2){
                    // 다시 처음 인덱스로 되돌리기
                    tmp.imageIndex = 0
                }
            }
        }
        postCount++
        //랜덤한 숫자를 하나 얻어낸다 (0~19 사이)
        val ranNum = ran.nextInt(20)
        if(ranNum == 10 && postCount > 15){
            postCount = 0
            //적기를 5개 1세트를 만들어서 List에 누적시키기
            for(i in 0 until 5){
                val tmp = Enemy()
                tmp.x = enemyX[i]
                tmp.y = -unitSize/2 // 임시 y 좌표
                tmp.type = ran.nextInt(2) // 적기의 type은 0 또는 1 랜덤하게 부여
                tmp.energy = if(tmp.type==0){ 50 } else { 100 }
                tmp.imageIndex = ran.nextInt(2) // 최초 이미지 인덱스도 랜덤하게 부여
                tmp.size = unitSize // 적기의 초기 크기 부여(추락할때는 줄이기 위해)
                // 만든 적기를 List에 누적시키기
                enemyList.add(tmp)
            }
        }
        //적기 움직이기
        for(tmp in enemyList){
            if(tmp.isFall){
                //크기를 줄이고
                tmp.size--
                //회전값을 증가
                tmp.angle += 10
                //만일 크기가 0보다 작아지면 ( 완전히 추락한 경우 )
                if(tmp.size <= 0){
                    tmp.isDead = true // 적기 객체를 제거하도록 표시한다.
                }
            } else {
                //적기의 y 좌표를 증가 시킨다
                tmp.y += missSpeed/2
            }
            if(tmp.y > height + unitSize/2){
                //배열에서 제거될 수 있도록 일단 표시해놓는다.
                tmp.isDead = true
            }
        }
        //적기를 체크해서 배열에서 제거할 적기는 제거하기
        for(i in enemyList.lastIndex downTo 0){
            // i번째 인덱스의 적기를 제거해야 한다면
            if(enemyList[i].isDead){
                // i번째 인덱스의 적기를 제거한다.
                enemyList.removeAt(i)
            }
        }
    }

    //미사일 관련 처리를 하는 함수
    fun missileService(){
        //미사일 만들기
        if(count%5 == 0){
            //현재 드래곤의 좌표값으로 미사일 객체를 생성한다.
            val missile = Missile(dragonX, dragonY)
            //List에 누적시키기
            missList.add(missile)
        }
        //미사일의 y좌표를 감소시켜서 위로 이동하도록 한다.
        for(tmp in missList){
            //미사일의 속도만큼 y좌표를 감소시킨다.
            tmp.y -= missSpeed
            //위쪽으로 화면을 벗어난 미사일이 제거 될 수 있도록
            if(tmp.y < -missSize/2){
                tmp.isDead = true
            }
        }
        //위쪽 밖으로 화면을 벗어난 미사일 제거
        for(i in missList.lastIndex downTo 0){
            if(missList.get(i).isDead){
                missList.removeAt(i)
            }
        }
    }


    //View가 차지하고 있는 영역의 width와 height가 전달되는 메소드
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //전달되는 폭과 높이를 필드에 저장
        width=w
        height=h
        //배경이미지의 초기 좌표
        back2Y = -height

        //unitSize 는 화면 폭의 1/5로 설정
        unitSize = w / 5
        dragonX = w / 2
        dragonY = height - unitSize / 2

        // 미사일의 크기는 드래곤의 크기의 1/4
        missSize = unitSize / 4

        missSpeed = height/50

        //초기화 함수 호출
        init()
    }
    //View에 터치 이벤트가 발생하면 호출되는 함수
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_MOVE -> {
                //이벤트가 일어난 곳의 x 좌표를 dragon의 좌표에 반영하기
                dragonX = event.x.toInt()
                dragonY = event.y.toInt()
            }
        }
        return true
    }
}







