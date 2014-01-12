import java.lang.Math;

class FeatureVector {
    
    //Declare variables
    private String name;
    private double[] features;
    private static boolean verbose = false;

    //Constructors
    //Constructor with size element
    public FeatureVector(String name, int size) {
		this.name = name;
		this.features = new double[size];
    }

    //Constructor with elements directly added in
    public FeatureVector(String name, double[] elems) {
		this.name = name;
		this.features = new double[elems.length];

		for (int i = 0; i < elems.length; i++) {
		    this.features[i] = elems[i];
		}
    }

    //minSize method - finds the smaller of the two vectors, will use in future methods
    private static int minSize(FeatureVector fv1, FeatureVector fv2) {
		int l;
		l = fv1.getSize();

		if (l > fv2.getSize()) {
		    l = fv2.getSize();
		}

		return l;
    }

    //getName method, returns the name of this object
    public String getName() {
		return this.name;
    }

    //getSize method, returns the length of the features array
    public int getSize() {
		return this.features.length;
    }

    //featuresAt array, returns a certain feature value
    public double featureAt(int index) {
		return this.features[index];
    }
    
    //featureSet method, sets a feature value
    public void featureSet(int index, double value) {
		this.features[index] = value;
    }
     
    //copy method, makes a hard copy of this featureVector object. 

    //NOTE - IS THIS MAKING A HARD COPY? 
    public FeatureVector copy() {
		FeatureVector fv = new FeatureVector(this.name, this.features);
		return fv;
    }

    //getDistance method - returns the distance between two featureVector object's features
    public double getDistance(FeatureVector other) {
		int l;
		double distance;
		l = FeatureVector.minSize(this, other);
		distance = 0.0;

		for (int i = 0; i < l; i++) {
		    distance = distance + Math.pow(other.featureAt(i) - featureAt(i), 2);
		}
		distance = Math.sqrt(distance);
		return distance;
    }

    //plus method, adds another objects values to this current one.  
    public void plus(FeatureVector other) {
		int l;
		l = FeatureVector.minSize(this, other);

		for (int i = 0; i < l; i++) {
		    this.features[i] += other.featureAt(i);
		}
    }

    //div method, divides our current object's features values by another objects values. 
    public void div(FeatureVector other) {
		int l = FeatureVector.minSize(this, other);
		for (int i = 0; i < l; i++) {
		    this.features[i] /= other.featureAt(i);
		}
    }
    
    //div method, divides by a pre-set value
    public void div(double value) {
		int l = getSize();
		for (int i = 0; i < l; i++) {
		    this.features[i] /= value;
		}
    }

    //toString method, returns a string with the object's values
    public String toString() {
		String str;
		int l;
		str = this.name;

		if (FeatureVector.verbose) {
		    str += ": {";
		    l = getSize();

		    for (int i = 0; i < l; i++) {
			if (i != 0) str += ", ";

			str += this.features[i];
		    }
		    str += "}";
		}
		return str;
    }

    //equals method, checks to see if two featureVectors are the same
    public boolean equals(FeatureVector other) {
		int l = this.getSize();
		
		for (int i = 0; i < l; i++) {
		    if (featureAt(i) != other.featureAt(i) || other == null || this.getSize() != other.getSize()) return false;
		}
		return true;
	    }

	    //setVerbose method, determins whether or not we will allow a string to be returned when toString is called
	    public static void setVerbose(boolean value) {
		FeatureVector.verbose = value;
    }
}
