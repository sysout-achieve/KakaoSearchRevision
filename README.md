# KakaoSearchRevision
kakao 도서 검색 프로젝트

<br>

### 구성 : <br>
- Language : Kotlin <br>
- DI : Hilt <br>
- AAC(MVVM, DataBinding, LiveData, Navigation) <br>
- Opensource: RxKotlin, RxBinding, Retrofit, Glide, Swiperefreshlayout <br>
- Test : Unit Test(JUnit4), Ui Test

<br>
<hr>
<br>

## 목표
- 간단한 입출력 프로젝트를 수행해보며 개인적으로 공부했던 내용들을 적용 및 정리
- **어떤 근거로 적용했는지, 장단점은 무엇인지, 다른 대안과의 비교, 앞으로 어떤 방향으로 개발할 것인지 고민**
- 정리 도중에 추가로 적용하거나 개선할 부분들을 찾아 Refactoring -> Refactoring을 통해 얻은 효과가 무엇인지 추가로 정리
- 디자인 패턴에 집중하는 것이 아니라 객체 지향에 집중할 것(디자인 패턴은 객체지향을 위한 도구)
<br>
<hr>

## Architecture
### MVVM

<img src="https://developer.android.com/topic/libraries/architecture/images/final-architecture.png" width="80%" height="80%" title="https://developer.android.com/jetpack/guide"></img>

<br>
Android Developer에서 AAC로 소개되었던 MVVM의 개념을 확인 <br>

- layer를 나눠서 각 계층이 가진 역할을 명확하게 하는 것을 강점으로 느낄 수 있었음 (각 계층에 구성된 객체가 하나의 책임을 갖게 함 - 단일책임원칙)
- layer의 계층별 역할을 명확하게 하는 것은 통일성 있는 코드를 만들 수 있음(협업에 효율적)
- viewmodel에 view관련(Activity, fragment) import를 제외시켜 model과 view의 loose coupling을 이룰 수 있음 
- jetpack의 AAC에 포함된 library들이 mvvm에서 특히 사용 및 관리를 효과적으로 할 수 있게 만들었다는 생각을 하게 됨
```
  ex) DataBinding-> xml에 데이터를 바인딩하여 데이터 객체와 뷰컴포넌트간 데이터 전달이 가능 
            : viewmodel에 ViewComponent 코드 차단 가능하게 함
      LiveDate-> ViewComponent에서 데이터 감지 
            : 바인딩한 viewmodel의 데이터를 간접적으로 사용 가능하게하여 데이터와 뷰의 분리 실현 
```
MVP와 차이점
: MVP패턴의 경우 View와 Model간의 상호작용을 Presenter를 매개로 동작

- Presenter와 View간의 loose coupling을 위한 interface구성
- MVP: Presenter와 View가 서로를 바라보고 기능 수행 vs MVVM: ViewModel은 데이터 변형과 비즈니스 로직에 대한 처리 + View는 화면의 IO만 관리하고 ViewModel을 관찰

<br>

#### 개인적인 생각 )<br>
> 그러나 View에서 입출력에 대한 내용을 Presenter에게 전달해야하는 과정에서 결합도가 올라감<br>
> MVP의 경우 Presenter라는 매개를 거쳐야만 하는 것이 단점으로 작용할 수 있음 <br>
```
  ex) 텍스트입력 후 입력한 데이터와 함께 화면전환 
   ( view에서 input받은 데이터 -> presenter로 데이터 전달하여 model 데이터 변경 -> 변경된 데이터 화면 전달 -> Intent or Bundle에 담아 화면 전환 ) 필요없는 과정이 추가될 수 있음
```
> 애플리케이션 개발자는 학문적인 연구를 하는 사람이 아니라 비즈니스를 위한 애플리케이션을 만드는 사람 -> 학문적인 방향이 아닌 성능과 유지보수측면에서 접근해야함 <br> 
> MVP와 MVVM 모두 애플리케이션을 만들기 위해 layer를 만들고 각 계층에 맞는 코드를 작성하게 하여 코드의 통일성과 이슈트래킹을 효과적으로 할 수 있는 디자인패턴들이라 생각함 <br>
> 복잡도가 높거나 높아질 가능성이 큰 프로젝트일수록 각 객체들간의 역할이 명확해야함 (부수효과를 막기 위해)<br>
> 숙련도가 떨어져도 디자인 패턴에 맞추어서 작업을 했을 때 가장 명확한 책임을 가지게 만드는 패턴이 효과적인 패턴이라 생각함<br>
> 패턴에 숙련되는 과정은 mvvm이 더 어렵다고 느껴짐, 대신 패턴이 지향하는 목표는 mvvm이 더 적합하다고 생각<br>
   > * 디자인패턴에 집중하는 것이 아니라 더 객체지향적으로 코딩하기 위해서 어떻게 사용하는 것이 더 적합할 것인지를 고민할 것
   > (개인프로젝트에서는 MVVM 활용 예정) 
<br>
<hr>  
<br>

### DataBinding, LiveData
  Kotlin extensions가 deprecated될 예정으로 DataBinding과 ViewBinding이 더 자주 사용될 예정 - [Link](https://android-developers.googleblog.com/2020/11/the-future-of-kotlin-android-extensions.html)
  
DataBinding을 사용하면 ViewModel과 뷰컴포넌트 객체를 연결지어주는 클래스를 generate함 <br>
ViewModel의 데이터 변화를 View에 notify()하는 구조로 code를 generate함 <br>
ViewModel과 xml을 연결시켜주는 것만으로 개발자가 직접 activity 소스에 변경된 데이터에 대한 행동을 작성할 필요없이 xml과 ViewModel만으로 관리할 수 있게 됨

LiveData
  : LiveData로 관찰하고자 하는 객체를 wrapping -> 객체의 변화를 View에서 관찰하여 그에 맞는 액션을 취할 수 있게 만들어짐
  (구조) Binding된 LiveData 객체가 가진 observe를 Activity에서 선언하여 화면에서 LiveData를 관찰, lifeCycle까지 Activity에서 관리할 수 있음  
  

  <br>
  <hr>
  
  <br>

### 예외처리

전체 프로세스에서 의도치 않은 에러가 나올 경우 <br>
<br>
<img src="https://miro.medium.com/max/1080/1*9hpEKzq--egTEixfwHve9Q.jpeg" width="50%" height="50%" title="https://miro.medium.com/max/1080/1*9hpEKzq--egTEixfwHve9Q.jpeg"></img>  <br>
이러한 화면을 사용자가 접하게 되면 사용자 경험에 안좋은 영향을 주게 되고 자주 일어난다면 사용자가 이탈하는 것은 뻔한 이야기다.
<br>
처리하지 못한 예외가 발생할 경우 애플리케이션은 

<img src="https://www.masterqna.com/android/?qa=blob&qa_blobid=7218132349570726514" width="50%" height="40%" title="https://www.masterqna.com/android/?qa=blob&qa_blobid=7218132349570726514"></img>  <br>
이런 로그를 던지면서 죽음을 맞이함<br>

위 로그를 발생시키는 시점에 예외처리를 만들어둘순 없을까라는 아이디어로 접근.<br>
App Kill이 일어나는 시점에 앱에서 제공하는 화면으로 에러를 알려주고 기존 앱의 화면으로 재진입할 수 있게 구현한다면 UX측면에서 덜 나쁜(오류가 안나는게 가장 좋음) 에러처리가 될 것이라 생각함
<br>
비슷한 고민을 먼저하신 선배개발자님의 블로그를 보고 예외처리를 커스텀하여 앱에 적용<br>
[박상권님의 블로그](https://medium.com/prnd/%EC%95%84%EB%A6%84%EB%8B%B5%EA%B2%8C-%EC%95%B1-%EC%98%A4%EB%A5%98-%EC%B2%98%EB%A6%AC%ED%95%98%EA%B8%B0-8bf9a46df515)

<br>
crashlytics와 같은 이슈트래킹 library를 적용하지 않은 프로젝트이기 때문에 DefaultUncaughtExceptionHandler에 예외처리에 대한 내용을 작성한 GlobalExceptionHandler를 만들어 적용<br>

기술 연습용 프로젝트임으로 사용자용 에러화면이 아닌 에러 로그와 되돌아가기만 구현하여 앱에 적용(필요에따라 커스텀 가능)
<br>

<img src="https://github.com/sysout-achieve/KakaoSearchRevision/blob/master/KakaoTalk_Photo_2021-01-20-17-48-18.jpeg?raw=true" width="32%" height="28%" title="https://github.com/sysout-achieve/KakaoSearchRevision/blob/master/KakaoTalk_Photo_2021-01-20-17-48-18.jpeg?raw=true"></img>  <br>
<br>
<hr>
