프로그램 요구사항 정리

플레이어 이름을 입력 받는다.
플레이어를 담을 객체를 생성.
이름이 같다면 같은 객체여야 한다.
플레이어의 이름 길이가 3으로 제한 미만 또는 초과는 예외 처리.

점수를 제외한 화면 출력

게임 진행
1. 투구 입력
2. 스트라이크인 경우 다음 프레임으로 넘어간다.
3. 스트라이크가 아닌 경우 투구 점수를 객체에 담은 후 같은 프레임에 한번더 투구 가능 (1번으로 이동)
4. 투구 결과에 따라 여러 값을 가짐 (spare, miss, gutter)
5. 총 10개의 프레임 11개 이상 생성 시 예외 처리

프레임
- 첫 투구시 10개의 핀 모두 쓰러트리면 10점, 스트라이크일 때 점수 확인 후 다음 프레임으로 넘어감.
- 처음 받은 점수가 10점 미만일 때 점수 확인 후 현재 프레임에서 한번 더 투구.
- 현재 프레임상태에서 두번째 투구일 때 첫번째 투구에서 쓰러트린 핀을 제외한 핀만 존재. 예) 첫투구시 6개가 쓰러졌을 때 다음엔 4개의 핀만 존재 얻을 수 있는 점수는 4점 이하.
- 현재 프레임상태에서 두번째 투구일 때 쓰러진 값에 따라서 점수 확인 후 여러 값을 가질 수 있음 spare, miss, gutter
- 프레임은 스트라이크가 아닐 시 2번의 투구를 할 수 있다.
- 10번 째 프레임에선 스트라이크여도 무조건 2번 이상 투구를 할 수 있다.
- 10번 째 프레임에서 스트라이크일 때 다음 프레임으로 넘어가지 않고 현재 프레임에서 한번 더 던진다.
- 10번 째 첫번째 프레임 or 두번째 프레임에서 스트라이크가 1번이상 or 스페어를 할 시 보너스투구를 던진다.
- 10번 째 보너스 투구는 같은 프레임에서 한번 더 던진다. (다른 프레임)
- 1~9번 째 프레임은 최소 1번, 최대 2번의 투구를 갖는다.
- 10번 째 프레임은 최소 2번, 최대 3번의 투구를 갖는다.

핀
- 점수는 0보다 작으면 예외
- 점수는 10보다 크면 예외
- 10개 모두 쓰러졌을 때 점수 확인 후 핀 객체 소멸?
- 10개 미만으로 쓰러졌을 때 점수 확인 후 현재 프레임에서 한번 더 투구.
- 두번 째 투구 시 현재 남아있는 핀보다 클 시 예외 처리
- 투번 째 투구 후 점수 확인 후 핀 객체 소멸?
- 10번 째 프레임은 스트라이크 및 스페어 시 딱 한번 10개의 핀을 갖는 객체가 생성
- 10번 째 보너스 핀 투구 후 점수 확인 후 핀 객체 소멸?

게임
10번 째 프레임 모두 끝나게 되면 프로그램 종료.
잘 못된 값을 입력 시 예외 처리


(out -> in)
이름 입력값을 통해 플레이어 객체를 생성 
플레이어 객체는 프레임을 가질 수 있다. or 프레임 인덱스와 맵핑할 수 있다.
투구를 하면 프레임 객체를 생성.
프레임객체는 볼링핀을 가질 수 있다.
볼링핀 객체 생성
볼링핀을 통해 점수를 확인 할 수 있다. (점수는 쓰러진 볼링핀에 따라서 결정이 된다.)
점수 객체 생성 

(in -> out)
점수는 볼링핀으로 전달
볼링핀은 프레임으로 전달
프레임은 전달 받은 데이터로 점수 확인.
다음 프레임인지, 현재 프레임에서 다시 투구 할 것인지

필요한 클래스
player
frame -> frames(bowling)
pin
score (점수 체크)

객체
플레이어
글자 길이가 3이 아닐 시 예외

프레임 객체는 점수와 남은 기회를 변수로 갖는다.
프레임은 크게 3개로 나뉜다
기본 프레임 (쓰러진 핀, 남은기회 = 1)
기본 프레임에서 확장 프레임 (쓰러진 핀, 남은 기회 0) -> (기본 프레임 쓰러진 핀 + 현재 쓰러진 핀 > 10 ) 일경우 예외 처리 
마지막 프레임 (쓰러진 핀, 남은 기회 = 2)
1. 스트라이크를 한번이라도 있으면 마지막 프레임 -> 확장 -> 확장
2. 프레임 -> 확장 프레임에서 스페어 일 시 -> 확장
3. 프레임 -> 확장 프레임에서 10점 미만일 시 종료.

핀들


핀
쓰러진 핀의 원시값 포장한 객체  
쓰러진 핀의 갯수가 10개 초과 일 때 예외처리  
쓰러진 핀의 갯수가 0 미만 일 때 예외 처리  

스코어
점수가 0 미만 일 때 예외  
점수가 10 초과 일 때예외  
점수를 판별하는 객체  
(점수, 남은 횟수)  
(10, 1) 스트라이크 -> X  
(첫프레임점수 + 확장프레임점수 = 10 , 0) 스페어 -> /  
(첫프레임점수, 1) 첫프레임 점수 -> n  
(첫프레임점수 = 0, 1) 거터 -> -  
(두번째 프레임점수 = 0, 0) 거터 -> -  
(첫프레임점수 + 확장프레임 점수 < 10) 미스 -> n|m   