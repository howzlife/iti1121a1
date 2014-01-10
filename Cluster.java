class Cluster {
    private FeatureVector[] elements;
    private int next;

    public Cluster(int capacity) {
	this.elements = new FeatureVector[capacity];
	this.next = 0;
    }

    private int minSize() {
	int min = getElementAt(0).getSize();
	for (int i=1;i<next;i++) {
	    if (min > getElementAt(i).getSize()) {
		min = getElementAt(i).getSize();
	    }
	}
	return min;
    }

    public int getSize() {
	return this.next;
    }

    public boolean add(FeatureVector elem) {
	if (next == elements.length) {
	    return false;
	}
	this.elements[next] = elem;
	next = next + 1;
	return true;
    }
    
    public FeatureVector getElementAt(int index) {
	return this.elements[index];
    }

    public FeatureVector getCentroid() {
	int l;
	FeatureVector centroid;
	if (next == 0) {
	    return null;
	}
	l = minSize();
	centroid = new FeatureVector("centroid", l);
	for (int i=0;i<next;i++) {
	    centroid.plus(getElementAt(i));
	}
	centroid.div((double)next);
	return centroid;
    }

    public double getVariance() {
	FeatureVector centroid = getCentroid();
	double variance = 0.0;
	for (int i=0;i<next;i++) {
	    variance = variance + centroid.getDistance(getElementAt(i));
	}
	return variance;
    }

    public String toString() {
	String str = "Cluster: {";
	for (int i=0;i<next;i++) {
	    if (i != 0)
		str += ", ";
	    str += getElementAt(i).toString();
	}
	str += "}";
	return str;
    }
}
