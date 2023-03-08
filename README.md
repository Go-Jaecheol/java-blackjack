# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

### 1단계 기능 목록

#### 이름 입력

- [x] 10글자 이상의 이름이 들어오면 예외 처리
- [x] 숫자, 특수문자가 들어오면 예외 처리
- [x] 게임 참여자 수는 딜러를 포함해 2명 이상 8명 이하이다.

#### 카드 생성

- [x] 값: A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K
- [x] 모양: 스페이드, 클로버, 다이아몬드, 하트
- [x] 값과 모양을 가지는 `domain.card.Card` 객체를 생성한다.
- [x] 위에 없는 값과 모양의 객체는 예외 처리한다.
- [x] `domain.card.Deck` 객체는 랜덤으로 카드를 반환한다.

#### 카드 받기

- [x] 모두 2장의 카드를 받는다.
- [x] 플레이어는 2장의 카드를 모두 공개한다.
- [x] **딜러**는 합계가 16점 이하면 반드시 1장의 카드를 추가로 받아야 한다.
- [x] **딜러**는 합계가 17점 이상이면 카드를 추가로 받을 수 없다.
- [x] 플레이어에게 카드를 더 받을 지 물어본다.
    - [x] 플레이어가 `y`를 입력하면, 카드를 하나 더 반환한다.
    - [x] 플레이어가 `n`을 입력하면, 넘어간다.
    - [x] `y, n`이 아닌 다른 문자가 들어오면 예외 처리한다.

#### 점수 집계

- [x] 모든 플레이어는 카드를 받을 때마다 점수를 추가해서 저장한다.
- [x] J, Q, K는 10으로 계산한다.
- [x] Ace는 11로 계산하되, 합계가 21이 넘는 경우 1로 계산한다.
- [x] 모든 딜러와 플레이어는 점수가 21을 초과하면 `Bust` 된다.

#### 결과 출력

- [x] 딜러가 `Bust`되지 않은 경우
    - [x] 딜러보다 낮은 점수를 가진 플레이어는 `패`이다.
    - [x] `Bust`한 플레이어는 `패`이다.
    - [x] 딜러보다 높은 점수를 가진 플레이어는 `승`이다.
    - [x] 딜러와 같은 점수를 가진 플레이어는 `무`이다.
- [x] 딜러가 `Bust`된 경우
    - [x] `Bust`한 플레이어는 `무`이다.
    - [x] `Bust`하지 않은 플레이어는 `승`이다.

---

### 1단계 리팩토링 요구 사항
#### 리팩토링
- [x] 모든 검증 메서드들을 호출하는 메서드 추가
- [x] 에러 메세지 형식 통일 및 상수화
- [x] 에러 메세지 출력 및 입력 반복
- [x] 실패 검증 테스트에서 어떤 예외 메세지 포함하는지 검증하도록 수정
- [x] printInitialMessage 메서드 로직 수정 (딜러 이름 출력하도록)
- [ ] shuffleDeck 메서드가 인자로 Deck을 받고, Deck을 반환하도록 변경
- [ ] FixedDeckShuffler 클래스 위치 이동
- [ ] initializeDeck 로직 수정 (Card 생성받는 부분)
- [ ] 이름으로 공백이 입력되는 경우에 대한 검증 추가
- [ ] BlackjackGame 클래스를 통한 controller 책임 분리
- [ ] 승무패 카운트 계산하는 로직 이동

#### 테스트
- PlayerCommandTest
  - [ ] isHit 테스트 추가
  - [ ] 생성 테스트 추가
  - [ ] from 예외 테스트 추가
- CardTest
  - [ ] 테스트 코드 분리
- DeckTest
  - [ ] 52개의 카드가 다 생성되었는지 확인하도록 테스트 수정
- NameTest
  - [ ] NullAndEmptySource를 활용해서 null 및 빈값 테스트 추가
  - [ ] 공백만 여러 개 들어오는 경우에 대해 테스트 추가
  - [ ] 지정한 최소 글자 아래의 글자가 들어오는 경우 테스트 추가
