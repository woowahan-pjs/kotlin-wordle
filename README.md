# 미션 - 워들

## 🚀 기능 요구 사항

선풍적인 인기를 끌었던 영어 단어 맞추기 게임이다.

## 입출력

- [ ] 입력
    - [ ] 알파벳을 입력받는다.
    - [ ] 잘못된 입력 시 재입력을 받는다.

- [ ] 출력
    - [ ] 게임 결과를 출력한다.
        - [ ] 게임 종료 시 시도횟수와 진행과정을 출력한다.

```text
// 예시

4/6

⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩
🟩🟩⬜🟩🟩
🟩🟩🟩🟩🟩
```

## 도메인

### 사전 / Word Pool

- [x] `words.txt`에 존재하는 단어들을 가지고 있다.

### 글자

- [x] ⚠️ 알파벳 소문자가 아니면 예외
- [x] ⚠️ 위치는 0 ~ 4까지의 값이 아니면 예외
- [x] 다른 글자와 일치하는지 비교

### 단어

- [x] ⚠️ 5글자가 아니면 예외
- [x] ⚠️ `words.txt`에 존재하지 않는 단어면 예외

### 정답 생성기

- [ ] `words.txt`에 존재하는 단어를 가져온다.
- [ ] ((현재 날짜 - 2021년 6월 19일) % 배열의 크기) 번째의 단어를 가져온다.

### 정답

- [ ] 정답생성기로부터 생성된 정답.
- [ ] 단어와 비교하여 결과를 생성한다.
    - [ ] 문자 검증 우선순위
        - [ ] 🟩 : 존재하고 위치도 같음 > 🟨 : 존재하나 위치가 다름 > ⬜ : 존재하지 않음

### 결과

- [ ] 불변
- [ ] 일치도를 가진다.
- [ ] 게임 성공여부를 알려준다.

#### 일치 상태 (Enum 형태로)

- [ ] GREEN : 존재하고 위치도 같음
- [ ] YELLOW : 존재하나 위치가 다름
- [ ] WHITE : 존재하지 않음

### 결과 묶음 (결과의 일급 컬렉션)

- [ ] 결과들을 가진다.

### 워들게임

- [ ] 게임 진행
- [ ] 6번이상 진행하면 실패!
- [ ] 게임 진행상태
    - [ ] 진행중, 성공, 실패
