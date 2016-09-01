package jp.itnav.moviesample;

import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    // Youtube API KEY
    static final String DEVELOPER_KEY ="YoutubeAPIKeyをここに入れる";

    private YouTubePlayer youTubePlayer;
    private YouTubePlayerView youTubePlayerView; // Youtube再生View
    private String youtubeId; //再生する動画のId

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        youtubeId = "-egC0t5UQFE";
    }
    /**
     * viewの生成
     */
    private void findView(){
        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(DEVELOPER_KEY, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        youTubePlayerView = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        findView();
    }

    /**
     * Yotube player初期化失敗
     */
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
        // 初期化失敗時の処理
    }
    /**
     * Yotube player初期化成功
     */
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider arg0, final YouTubePlayer player, boolean arg2) {
        youTubePlayer = player;

        // 動画の読み込み
        youTubePlayer.loadVideo(youtubeId);

        // 再生ボタンを表示しないスタイルを指定(コメントアウトすればボタンが表示される)
        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);

        // 動画再生の状態を管理するリスナ
        youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {
                // 読込中の処理
            }

            @Override
            public void onLoaded(String s) {
                // 読み込み完了の処理
            }

            @Override
            public void onAdStarted() {
                // 広告の再生が開始された時の処理
            }

            @Override
            public void onVideoStarted() {
                // 動画が開始された時の処理
            }

            @Override
            public void onVideoEnded() {// 動画が終了した時の処理
                youTubePlayer.play(); // 動画が終了したら、再生し直す
            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {

            }
        });
    }
}
