package e.jakubsiembida.ithinkthiswillbethelastone;

import android.graphics.Bitmap;
import android.util.Log;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class DigitGuesser {

    private static final int BITMAP_SIDE_LENGTH = 28;

    private DigitGuessNeuralNetwork neuralNetwork = new DigitGuessNeuralNetwork();


    public int guessDigit(Bitmap bitmap){
        Bitmap resized_bitmap = resizeBitmap(bitmap);


        double[] output = neuralNetwork.processSignal(getNeuralNetInput(resized_bitmap));
        int guess = -1;
        double max = -1;

        if(output != null){

            for (int i = 0; i < output.length; i++) {
                if(output[i] > max){
                    max = output[i];
                    guess = i;
                }
            }
            return guess;
        } else {
            throw new IllegalArgumentException("The input is not appropriate");
        }


    }

    private Bitmap resizeBitmap(Bitmap bitmap){
        return Bitmap.createScaledBitmap(bitmap, BITMAP_SIDE_LENGTH, BITMAP_SIDE_LENGTH, false);
    }


    private double[] getNeuralNetInput(Bitmap b) {
        int bytes = b.getRowBytes() * b.getHeight();
        ByteBuffer buffer = ByteBuffer.allocate(bytes);
        b.copyPixelsToBuffer(buffer);

        byte[] values = buffer.array();
        buffer.clear();



        double[] output = new double[values.length];
        for(int i = 0; i < values.length; i++){
            if(values[i] != 0) {
                output[i] = 1;
            } else {
                output[i] = 0;
            }
        }
        System.out.println(Arrays.toString(output));
        return output;
    }



}

