class Cluster {

	// Declare variables
    private FeatureVector[] elements;
    private int next;

    //Constructor
    public Cluster(int capacity) {
		this.elements = new FeatureVector[capacity];
		this.next = 0;
    }

    //Methods

    //method to find smallest cluster object, will use in further methods
    private int minSize() {
	int min = getElementAt(0).getSize();

		for (int i = 1; i < next; i++) {

		    if (min > getElementAt(i).getSize()) {
				min = getElementAt(i).getSize();
		    }
		}
		return min;
    }

    //getSize method
    public int getSize() {
		return this.next;
    }

    //add method, to add objects to the elements[] array
    public boolean add(FeatureVector elem) {

		if (next == elements.length) {
		    return false;
		}
		this.elements[next] = elem;
		next = next + 1;
		return true;
    }
    
    //getElementAt method, returns a FeatureVector object
    public FeatureVector getElementAt(int index) 
    {
		return this.elements[index];
    }

    //getCentroid method, returns an object with values equal to the average of other FeatureVector objects
    public FeatureVector getCentroid() {
		int l;
		FeatureVector centroid;

		if (next == 0) {
		    return null;
		}

		l = minSize();
		centroid = new FeatureVector("centroid", l);

		for (int i = 0; i < next; i++) {
		    centroid.plus(getElementAt(i));
		}
		centroid.div((double)next);
		return centroid;
    }

    //getVariance method - returns the variance 
    public double getVariance() {
		FeatureVector centroid = getCentroid();
		double variance = 0.0;

		for (int i = 0; i < next; i++) {
		    variance = variance + centroid.getDistance(getElementAt(i));
		}
		return variance;
    }

    public String toString() {

		String str = "Cluster: {";

		for (int i = 0; i < next; i++) {

		    if (i != 0) str += ", ";
		    str += getElementAt(i).toString();
		}
		str += "}";
		return str;
    }
}
