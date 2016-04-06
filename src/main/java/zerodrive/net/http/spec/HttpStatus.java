package zerodrive.net.http.spec;


/**
 * HTTP ステータスコードの列挙型です。
 * 
 * @author AdachiHjm
 * @created 2015/06/30 0:17:06
 *
 */
public enum HttpStatus {
    //======================================================================
    // 1xx Informational 情報
    /**
     * 継続。
     * クライアントはリクエストを継続できる。サーバがリクエストの最初の部分を受け取り、まだ拒否していないことを示す。
     */
    Continue(100),

    /**
     * プロトコル切替え。
     * サーバはリクエストを理解し、遂行のためにプロトコルの切替えを要求している。
     */
    Switching_Protocols(101),

    /**
     * 処理中。
     * WebDAV の拡張ステータスコード。処理が継続されて行われていることを示す。
     */
    Processing(102),

    //======================================================================
    // 2xx Success 成功
    /**
     * OK。
     * リクエストは成功し、レスポンスとともに要求に応じた情報が返される。
     */
    OK(200),

    /**
     * 作成。
     * リクエストは完了し、新たに作成されたリソースのURIが返される。
     */
    Created(201),

    /**
     * 受理。
     * リクエストは受理されたが、処理は完了していない。
     */
    Accepted(202),

    /**
     * 信頼できない情報。
     * オリジナルのデータではなく、ローカルやプロキシ等からの情報であることを示す。
     */
    Non_Authoritative_Information(203),

    /**
     * 内容なし。
     * リクエストを受理したが、返すべきレスポンスエンティティが存在しない場合に返される。
     */
    No_Content(204),

    /**
     * 内容のリセット。
     * リクエストを受理し、ユーザエージェントの画面をリセットする場合に返される。
     */
    Reset_Content(205),

    /**
     * 部分的内容。
     * 部分的 GET リクエストを受理したときに、返される。
     */
    Partial_Content(206),

    /**
     * 複数のステータス。
     * WebDAV の拡張ステータスコード。
     */
    Multi_Status(207),

    /**
     * 既に報告。
     * WebDAV の拡張ステータスコード。
     */
    Already_Reported(208),

    /**
     * IM 使用。
     * Delta encoding in HTTP の拡張ステータスコード。
     */
    IM_Used(226),

    //======================================================================
    // 3xx Redirection リダイレクション
    /**
     * 複数の選択。
     * リクエストしたリソースが複数存在し、ユーザやユーザーエージェントに選択肢を提示するときに返される。
     */
    Multiple_Choices(300),

    /**
     * 恒久的に移動した。
     * リクエストしたリソースが恒久的に移動されているときに返される。Location ヘッダに移動先の URL が示されている。
     */
    Moved_Permanently(301),

    /**
     * 発見した。
     * リクエストしたリソースが一時的に移動されているときに返される。Location ヘッダに移動先の URL が示されている。
     */
    Found(302),

    /**
     * 他を参照せよ。
     * リクエストに対するレスポンスが他の URL に存在するときに返される。Location ヘッダに移動先の URL が示されている。
     */
    See_Other(303),

    /**
     * 未更新。
     * リクエストしたリソースは更新されていないことを示す。
     */
    Not_Modified(304),

    /**
     * プロキシを使用せよ。
     * レスポンスの Location ヘッダに示されるプロキシを使用してリクエストを行わなければならないことを示す。
     */
    Use_Proxy(305),

    /**
     * 一時的リダイレクト。
     * リクエストしたリソースは一時的に移動されているときに返される。Location ヘッダに移動先の URL が示されている。
     */
    Temporary_Redirect(307),

    /**
     * 恒久的リダイレクト。
     */
    Permanent_Redirect(308),

    //======================================================================
    // 4xx Client Error クライアントエラー
    /**
     * リクエストが不正である。
     * 定義されていないメソッドを使うなど、クライアントのリクエストがおかしい場合に返される。
     */
    Bad_Request(400),

    /**
     * 認証が必要である。
     * Basic 認証や Digest 認証などを行うときに使用される。
     */
    Unauthorized(401),

    /**
     * 支払いが必要である。
     * 現在は実装されておらず、将来のために予約されているとされる。
     */
    Payment_Required(402),

    /**
     * 禁止されている。
     * リソースにアクセスすることを拒否された。リクエストはしたが処理できないという意味。
     * アクセス権がない場合や、ホストがアクセス禁止処分を受けた場合などに返される。
     */
    Forbidden(403),

    /**
     * 未検出。
     * リソースが見つからなかった。
     */
    Not_Found(404),

    /**
     * 許可されていないメソッド。
     * 許可されていないメソッドを使用しようとした。
     */
    Method_Not_Allowed(405),

    /**
     * 受理できない。
     * Accept 関連のヘッダに受理できない内容が含まれている場合に返される。
     */
    Not_Acceptable(406),

    /**
     * プロキシ認証が必要である。
     * プロキシの認証が必要な場合に返される。
     */
    Proxy_Authentication_Required(407),

    /**
     * リクエストタイムアウト。
     * リクエストが時間以内に完了していない場合に返される。
     */
    Request_Timeout(408),

    /**
     * 矛盾。
     * 要求は現在のリソースと矛盾するので完了出来ない。
     */
    Conflict(409),

    /**
     * 消滅した。
     * リソースは恒久的に移動・消滅した。どこに行ったかもわからない。
     */
    Gone(410),

    /**
     * 長さが必要。
     * Content-Length ヘッダがないのでサーバがアクセスを拒否した場合に返される。
     */
    Length_Required(411),

    /**
     * 前提条件で失敗した。
     * 前提条件が偽だった場合に返される。
     */
    Precondition_Failed(412),

    /**
     * リクエストエンティティが大きすぎる。
     * リクエストエンティティがサーバの許容範囲を超えている場合に返す。
     */
    Request_Entity_Too_Large(413),

    /**
     * リクエスト URI が大きすぎる。
     * URI が長過ぎるのでサーバが処理を拒否した場合に返す。
     */
    Request_URI_Too_Long(414),

    /**
     * サポートしていないメディアタイプ。
     * 指定されたメディアタイプがサーバでサポートされていない場合に返す。
     */
    Unsupported_Media_Type(415),

    /**
     * リクエストしたレンジは範囲外にある。
     * 実リソースのサイズを超えるデータを要求した。
     */
    Requested_Range_Not_Satisfiable(416),

    /**
     * Expect ヘッダによる拡張が失敗。
     * その拡張はレスポンスできない。またはプロキシサーバは、次に到達するサーバがレスポンスできないと判断している。
     */
    Expectation_Failed(417),

    /**
     * 私はティーポット。
     * HTCPCP/1.0 の拡張ステータスコード。
     * ティーポットにコーヒーを淹れさせようとして、拒否された場合に返すとされる、ジョークのコードである。
     */
    Im_a_teapot(418),

    /**
     * 処理できないエンティティ。
     * WebDAV の拡張ステータスコード。
     */
    Unprocessable_Entity(422),

    /**
     * ロックされている。
     * WebDAV の拡張ステータスコード。リクエストしたリソースがロックされている場合に返す。
     */
    Locked(423),

    /**
     * 依存関係で失敗。
     * WebDAV の拡張ステータスコード。
     */
    Failed_Dependency(424),

    /**
     * アップグレード要求。
     * Upgrading to TLS Within HTTP/1.1 の拡張ステータスコード。
     */
    Upgrade_Required(426),

    //======================================================================
    // 5xx Server Error サーバエラー
    /**
     * サーバ内部エラー。
     * サーバ内部にエラーが発生した場合に返される。
     */
    Internal_Server_Error(500),

    /**
     * 実装されていない。
     * 実装されていないメソッドを使用した。
     */
    Not_Implemented(501),

    /**
     * 不正なゲートウェイ。
     * ゲートウェイ・プロキシサーバは不正な要求を受け取り、これを拒否した。
     */
    Bad_Gateway(502),

    /**
     * サービス利用不可。
     * サービスが一時的に過負荷やメンテナンスで使用不可能である。
     */
    Service_Unavailable(503),

    /**
     * ゲートウェイタイムアウト。
     * ゲートウェイ・プロキシサーバはURIから推測されるサーバからの適切なレスポンスがなくタイムアウトした。
     */
    Gateway_Timeout(504),

    /**
     * サポートしていない HTTP バージョン。
     * リクエストがサポートされていない HTTP バージョンである場合に返される。
     */
    HTTP_Version_Not_Supported(505),

    /**
     * Transparent Content Negotiation in HTTP で定義されている拡張ステータスコード。
     */
    Variant_Also_Negotiates(506),

    /**
     * 容量不足。
     * WebDAV の拡張ステータスコード。リクエストを処理するために必要なストレージの容量が足りない場合に返される。
     */
    Insufficient_Storage(507),

    /**
     * 帯域幅制限超過。
     * そのサーバに設定されている帯域幅（転送量）を使い切った場合に返される。
     */
    Bandwidth_Limit_Exceeded(509),

    /**
     * 拡張できない。
     * An HTTP Extension Framework で定義されている拡張ステータスコード。
     */
    Not_Extended(510);


    //======================================================================
    // Fields
    private final int statusCode;


    //======================================================================
    // Constructors
    private HttpStatus(int _statusCode) {
        this.statusCode = _statusCode;
    }


    //======================================================================
    // Methods
    public int getStatusCode() {
        return this.statusCode;
    }

    public static HttpStatus valueOf(int _statusCode) {
        for (HttpStatus status : HttpStatus.values()) {
            if (status.getStatusCode() == _statusCode) {
                return status;
            }
        }
        // FIXME: i18n
        throw new IllegalArgumentException("Unknown status code.[" + _statusCode + "]");
    }
}