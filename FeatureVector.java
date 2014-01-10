import java.lang.Math;

class FeatureVector {
    
    private String name;
    private double[] features;
    private static boolean verbose = false;

    public FeatureVector(String name, int size) {
	this.name = name;
	this.features = new double[size];
    }

    public FeatureVector(String name, double[] elems) {
	this.name = name;
	this.features = new double[elems.length];
	for (int i=0;i<elems.length;i++) {
	    this.features[i] = elems[i];
	}
    }

    private static int minSize(FeatureVector fv1, FeatureVector fv2) {
	int l;
	l = fv1.getSize();
	if (l > fv2.getSize()) {
	    l = fv2.getSize();
	}
	return l;
    }

    public String getName() {
	return this.name;
    }

    public int getSize() {
	return this.features.length;
    }

    public double featureAt(int index) {
	return this.features[index];
    }
    
    public void featureSet(int index, double value) {
	this.features[index] = value;
    }
    
    public FeatureVector copy() {
	FeatureVector fv = new FeatureVector(this.name, this.features);
	return fv;
    }

    public double getDistance(FeatureVector other) {
	int l;
	double distance;
	l = FeatureVector.minSize(this, other);
	distance = 0.0;
	for (int i=0;i<l;i++) {
	    distance = distance + Math.pow(other.featureAt(i) - featureAt(i), 2);
	}
	distance = Math.sqrt(distance);
	return distance;
    }

    public void plus(FeatureVector other) {
	int l;
	l = FeatureVector.minSize(this, other);
	for (int i=0;i<l;i++) {
	    this.features[i] += other.featureAt(i);
	}
    }

    public void div(FeatureVector other) {
	int l = FeatureVector.minSize(this, other);
	for (int i=0;i<l;i++) {
	    this.features[i] /= other.featureAt(i);
	}
    }
    
    public void div(double value) {
	int l = getSize();
	for (int i=0;i<l;i++) {
	    this.features[i] /= value;
	}
    }

    public String toString() {
	String str;
	int l;
	str = this.name;
	if (FeatureVector.verbose) {
	    str += ": {";
	    l = getSize();
	    for (int i=0;i<l;i++) {
		if (i != 0)
		    str += ", ";
		str += this.features[i];
	    }
	    str += "}";
	}
	return str;
    }

    public boolean equals(FeatureVector other) {
	int l = this.getSize();
	if (other == null || this.getSize() != other.getSize()) {
	    return false;
	}
	for (int i=0;i<l;i++) {
	    if (featureAt(i) != other.featureAt(i)) {
		return false;
	    }
	}
	return true;
    }

    public static void setVerbose(boolean value) {
	FeatureVector.verbose = value;
    }
}
