package MatlabInJava;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MatlabInJava {
    private MatlabEngine matlabEngine;

    public MatlabInJava() {
        try {
            matlabEngine = MatlabEngine.startMatlab();
        } catch (EngineException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addNoiseToImage(String inputPath,double dencity)
    {
        try {
            System.out.println("test");
            matlabEngine.eval("I = imread('"+inputPath+"');");
            matlabEngine.eval("Noise = zeros(size(I,1),size(I,2));");
            matlabEngine.eval("Noise = uint8(imnoise(Noise,'salt & pepper',"+dencity+")*255);");
            matlabEngine.eval("I(:,:,1) = I(:,:,1)+ Noise;");
            matlabEngine.eval("I(:,:,2) = I(:,:,2)+ Noise;");
            matlabEngine.eval("I(:,:,3) = I(:,:,3)+ Noise;");
            matlabEngine.eval("imwrite(I,'"+inputPath+"')");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
    /*
    type == 1 => linear
    type == 2 => median
    */
    public void cleanNoise(String inputPath,String name,int type,int count)
    {
        try {
            matlabEngine.eval("I = imread('"+inputPath+"');");
            if(type==1)
                matlabEngine.eval("Mask = (1/16)*[1 2 1;2 4 2;1 2 1];");
            for(int i=0;i<count;i++)
            {
                if(type==1)
                {
                    matlabEngine.eval("I(:,:,1) = filter2(Mask,I(:,:,1));");
                    matlabEngine.eval("I(:,:,2) = filter2(Mask,I(:,:,2));");
                    matlabEngine.eval("I(:,:,3) = filter2(Mask,I(:,:,3));");
                }
                else
                {
                    matlabEngine.eval("I(:,:,1) = medfilt2(I(:,:,1),[3 3], 'symmetric');");
                    matlabEngine.eval("I(:,:,2) = medfilt2(I(:,:,2),[3 3], 'symmetric');");
                    matlabEngine.eval("I(:,:,3) = medfilt2(I(:,:,3),[3 3], 'symmetric');");
                }
            }
            matlabEngine.eval("imwrite(I,'"+name+".jpg')");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }



}
