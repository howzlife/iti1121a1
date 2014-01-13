import java.util.Random;

class Clustering {

	//kmeans method, only method in this class
    public static Cluster[] kmeans(FeatureVector[] examples, int k) {
	
	//declare variables
		Cluster[] means, newmeans;
		Random generator = new Random();
		int[] alreadypicked = new int[k];
		int c, d, h, i, j, r;
		double distance;

		j = 0;
		c = examples.length;
		means = null;

	//outer loop of the algorithm
		while (true) {

	//create k clusters, newmeans is our D
		    newmeans = new Cluster[k];

	//choose k clusters randomly from the n input examples
		    for (i = 0; i < k; i++) {
				do {
				    r = generator.nextInt(examples.length);
				    for (j = 0; j < i; j++) {
						if (alreadypicked[j] == r) break;	
				    }
				} while (j != i);

				newmeans[i] = new Cluster(c);
				newmeans[i].add(examples[r]);
				alreadypicked[i] = r;
		    }

		    for (i = 0; i < examples.length; i++) {

				for (j = 0; j < k; j++) {

				    if (alreadypicked[j] == i) break;
				    
				}

				if (j != k) continue;

	//for each input example, find the cluster of D (newmeans) with the minimum centroid distance
				distance = examples[i].getDistance(newmeans[0].getCentroid());
				d = 0;
				for (j = 1; j < k; j++) {

				    if (distance > examples[i].getDistance(newmeans[j].getCentroid())) {
						distance = examples[i].getDistance(newmeans[j].getCentroid());
						d = j;
				    }
				}
				newmeans[d].add(examples[i]);
		    }

		    h = 0;
		    
		    if (means == null) {
				means = newmeans;
				continue;
		    }

	//if D and D' are the same, break outer loop - both are the same, and we have found our optimal clusters
		    for (i = 0; i < k; i++) {
				for (j = 0; j < k; j++) {
				    if (means[j].getSize() == newmeans[i].getSize() && means[j].
				        getCentroid().equals(newmeans[i].getCentroid())) {
						h++;
						break;
				    }
				}
		    }

		    if (h == k) break;
		}
		return means;
    }
}
