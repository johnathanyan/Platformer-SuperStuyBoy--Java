import java.util.*;
import java.io.*;

public class Pixelator{
    private int multiplier;
    private int[][] input;
    private int[][] result;
    private int size;

    public Pixelator(int multi, int[][] input){
	multiplier = multi ;
	this.input = input;
        this.size = input[0].length;
	result = new int[input.length * multiplier][];
    }

    public int[][] work(){
	for(int i = 0; i < input.length; i++){
	    int[] a = input[i];
	    int[] arr = new int[size * multiplier];
	    int delta = i * multiplier;

	    result[delta] = arr;

	    for(int j = 0; j < size; j++){
		int delta2 = j*multiplier;
		Arrays.fill(arr, delta2, delta2 + multiplier, a[j]);
	    }

	    for(int j = 1; j < multiplier; j++){
		result[delta + j] = Arrays.copyOf(arr, arr.length);
	    }
	}
	return result;
    }


}
