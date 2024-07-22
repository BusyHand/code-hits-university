package ru.university.hits.code.module_2;
import java.util.Scanner;

class K_наименьшее {

	public static void main(String[] args) {
		long currentTimeMillis1 = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		BoundedHeap boundedHeap = new BoundedHeap(k);

		for (int i = 0; i < n; i++) {
			boundedHeap.add(sc.nextInt());
		}
		int minK = boundedHeap.getMinK();
		System.out.println(minK);
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println(currentTimeMillis2 - currentTimeMillis1);
	}

	private static class BoundedHeap {

		private final int[] heap;

		private final int maxSize;

		private int size;

		public BoundedHeap(int k) {
			heap = new int[k];
			size = 0;
			maxSize = k;
		}

		public void add(int value) {

			if (size < maxSize) {
				heap[size] = value;
				siftUp(size);
				size++;
			} else if (value < heap[0]) {
				heap[0] = value;
				siftDown(0);
			}
		}

		private void siftUp(int index) {
			int parentIndex = (index - 1) / 2;

			while (index > 0 && heap[index] > heap[parentIndex]) {
				swap(index, parentIndex);
				index = parentIndex;
				parentIndex = (index - 1) / 2;
			}
		}

		private void siftDown(int index) {
			int leftChildIndex = 2 * index + 1;
			int rightChildIndex = 2 * index + 2;
			int largestIndex = index;

			if (leftChildIndex < size && heap[leftChildIndex] > heap[largestIndex]) {
				largestIndex = leftChildIndex;
			}

			if (rightChildIndex < size && heap[rightChildIndex] > heap[largestIndex]) {
				largestIndex = rightChildIndex;
			}

			if (largestIndex != index) {
				swap(index, largestIndex);
				siftDown(largestIndex);
			}
		}

		private void swap(int i, int j) {
			int temp = heap[i];
			heap[i] = heap[j];
			heap[j] = temp;
		}

		private int getMinK() {
			return heap[0];
		}
	}
}
