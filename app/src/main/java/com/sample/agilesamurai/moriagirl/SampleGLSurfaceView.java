package com.sample.agilesamurai.moriagirl;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import jp.live2d.android.Live2DModelAndroid;
import jp.live2d.android.UtOpenGL;
import jp.live2d.framework.L2DPhysics;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.content.res.AssetManager;
import jp.live2d.motion.Live2DMotion;
import jp.live2d.motion.MotionQueueManager;

public class SampleGLSurfaceView extends GLSurfaceView{
    public SampleGLRenderer renderer ;
    Context con;
    Live2DMotion motion;
    String motionFilePath = "";

    public SampleGLSurfaceView(Context context, String str)
    {
        super(context);
        con = context;
        motionFilePath = str;
        renderer = new SampleGLRenderer();
        //setRenderer(renderer);
    }


    class SampleGLRenderer implements Renderer
    {
        private Live2DModelAndroid live2DModel ;
        L2DPhysics physics;
        MotionQueueManager motionMgr = new MotionQueueManager();

        private final String MODEL_PATH = "haru/haru.moc" ;
        private final String TEXTURE_PATHS[] =
                {
                        "haru/haru.1024/texture_00.png" ,
                        "haru/haru.1024/texture_01.png" ,
                        "haru/haru.1024/texture_02.png"
                } ;
        String MOTION_PATH=motionFilePath;
        final String PHYSICS_PATH="haru/haru.physics.json";

        @Override
        public void onDrawFrame(GL10 gl)
        {
            gl.glMatrixMode(GL10.GL_MODELVIEW ) ;
            gl.glLoadIdentity() ;
            gl.glClear( GL10.GL_COLOR_BUFFER_BIT ) ;
//            gl.glEnable( GL10.GL_BLEND ) ;
//            gl.glBlendFunc( GL10.GL_ONE , GL10.GL_ONE_MINUS_SRC_ALPHA ) ;
//            gl.glDisable( GL10.GL_DEPTH_TEST ) ;
//            gl.glDisable( GL10.GL_CULL_FACE ) ;

            live2DModel.loadParam();

            if(motionMgr.isFinished())
            {
                motionMgr.startMotion(motion, false);
            }
            else
            {
                motionMgr.updateParam(live2DModel);
            }

            live2DModel.saveParam();

            physics.updateParam(live2DModel);

            live2DModel.setGL( gl ) ;

            live2DModel.update() ;
            live2DModel.draw() ;
        }


        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height)
        {
            gl.glViewport( 0 , 0 , width , height ) ;

            gl.glMatrixMode( GL10.GL_PROJECTION ) ;
            gl.glLoadIdentity() ;

            float modelWidth = live2DModel.getCanvasWidth();
            float visibleWidth = modelWidth * (3.0f/4.0f);
            float margin = 0.5f * ( modelWidth/4.0f ) ;

            gl.glOrthof(margin, margin+visibleWidth, visibleWidth*height/width, 0, 0.5f, -0.5f);
        }


        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config)
        {
            AssetManager mngr = con.getAssets();
            try
            {
                InputStream in = getContext().getAssets().open( MODEL_PATH ) ;
                live2DModel = Live2DModelAndroid.loadModel( in ) ;
                in.close() ;

                for (int i = 0 ; i < TEXTURE_PATHS.length ; i++ )
                {
                    InputStream tin = getContext().getAssets().open( TEXTURE_PATHS[i] ) ;
                    int texNo = UtOpenGL.loadTexture(gl , tin , true ) ;
                    live2DModel.setTexture( i , texNo ) ;
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                InputStream in = mngr.open( MOTION_PATH ) ;
                motion = Live2DMotion.loadMotion( in ) ;
                in.close() ;

                in=mngr.open(PHYSICS_PATH);
                physics=L2DPhysics.load(in);
                in.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
