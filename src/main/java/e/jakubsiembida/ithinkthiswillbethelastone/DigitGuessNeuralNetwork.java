package e.jakubsiembida.ithinkthiswillbethelastone;

import org.neuroph.core.NeuralNetwork;

import java.util.Arrays;

public class DigitGuessNeuralNetwork {


    private NeuralNetwork neuralNetwork;



    public DigitGuessNeuralNetwork(){

            neuralNetwork=NeuralNetwork.load(MainActivity.neuralNetInputStream);

    }


    public double[] processSignal(double[] input){
        System.out.println(input.length);
        if(input.length == 784){
            neuralNetwork.setInput(input);
            neuralNetwork.calculate();
            double[] output = neuralNetwork.getOutput();
            String s = "";
            for(double d : output){
                s += String.valueOf(d) + ",";
            }
            System.out.println(s);
            return output;
        } else {
            return null;
        }
    }

}
