public int longestPath(int[][] matrix) {
	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
		return 0;
	}

	int m = matrix.length;
	int n = matrix[0].length;
	int[][] maxLength = new int[m][n];
	int max = 0;

	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			max = Math.max(dfs(matrix, maxLength, i, j));
		}
	}

	return result[0];
}

private int dfs(int[][] matrix, int[][] maxLength, int i, int j) {
	if (maxLength[i][j] > 0) {
		return maxLength[i][j];
	}

	int result = 0;

	if (i - 1 >= 0 && matrix[i][j] > matrix[i - 1][j]) {
		result = Math.max(result, dfs(matrix, maxLength, i - 1, j));
	}

	if (i + 1 < matrix.length && matrix[i][j] > matrix[i + 1][j]) {
		result = Math.max(result, dfs(matrix, maxLength, i + 1, j));
	}

	if (j - 1 >= 0 && matrix[i][j] > matrix[i][j - 1]) {
		result = Math.max(result, dfs(matrix, maxLength, i, j - 1));
	}

	if (j + 1 < matrix[0].length && matrix[i][j] > matrix[i][j + 1]) {
		result = Math.max(result, dfs(matrix, maxLength, i, j + 1));
	}

	maxLength[i][j] = 1 + result;
	return 1 + result;
}