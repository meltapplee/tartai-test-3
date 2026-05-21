
## 서버 URL
>BaseURL: https://tartai-test-3.onrender.com/

## 기술 스택

| 구분         | 기술                             |
|------------|--------------------------------|
| 언어         | Kotlin 2.2.21                  |
| 프레임워크      | Spring Boot 4.0.6              |
| JVM        | Java 21                        |
| Database   | PostgreSQL (Supabase)          |
| ORM        | Spring Data JPA / Hibernate    |
| Docs       | SpringDoc OpenAPI (Swagger UI) |
| Infra      | Render (서버), Supabase (DB)     |
| Code Style | ktlint 1.5.0                   |

## 주요 기능

### 주문/상태 조회
- 주문 목록 조회 (페이지네이션)
- 주문 상세 조회
- 주문 상태 변경 (이전 상태로 되돌리기 불가, 앞 단계로는 건너뛰기 가능)

| 현재 상태     | 변경 가능한 상태                                      |
|-----------|------------------------------------------------|
| PENDING   | PAID, PREPARING, SHIPPED, DELIVERED, CANCELLED |
| PAID      | PREPARING, SHIPPED, DELIVERED, CANCELLED       |
| PREPARING | SHIPPED, DELIVERED, CANCELLED                  |
| SHIPPED   | DELIVERED, CANCELLED                           |
| DELIVERED | 변경 불가                                          |
| CANCELLED | 변경 불가                                          |

### 공지/게시판
- 게시글 작성 / 조회 / 삭제

### 실시간 알림
- SSE(Server-Sent Events) 기반 주문 상태 실시간 수신
- 주문 ID 단위 구독, 상태 변경 시 즉시 이벤트 전송
- `DELIVERED` / `CANCELLED` 상태 도달 시 서버에서 연결 자동 종료

## API 목록

### 주문/상태 조회 API
| Method | Endpoint                   | 설명       |
|--------|----------------------------|----------|
| GET    | `/orders`                  | 주문 목록 조회 |
| GET    | `/orders/{orderId}`        | 주문 상세 조회 |
| PATCH  | `/orders/{orderId}/status` | 주문 상태 변경 |

### 공지/게시판 API
| Method | Endpoint          | 설명        |
|--------|-------------------|-----------|
| POST   | `/posts`          | 게시글 작성    |
| GET    | `/posts`          | 게시글 목록 조회 |
| GET    | `/posts/{postId}` | 게시글 상세 조회 |
| DELETE | `/posts/{postId}` | 게시글 삭제    |

### 알림 API
| Method | Endpoint                             | 설명              |
|--------|--------------------------------------|-----------------|
| GET    | `/notifications/subscribe/{orderId}` | 주문 상태 변경 SSE 구독 |


## API 문서
>https://tartai-test-3.onrender.com/swagger-ui/index.html

## 주의사항
Render 무료 인스턴스는 비활성 상태 시 자동으로 슬립 상태가 됩니다.                                                                                                      
슬립 후 첫 요청 시 50초 이상 지연이 발생할 수 있습니다.

## 환경 변수

```env
DB_URL=
DB_USERNAME=
DB_PASSWORD=
```

## 테스트
잘못된 상태 전이를 허용하면 데이터 정합성이 깨지기 때문에, 주문 상태 전이 로직(`OrderStatus.canTransitionTo`)에 단위 테스트를 붙였습니다.