public String canBeSorted(int[] nums){
   if(nums.length == 1) return "yes";
      String res = "No";
      int startIndex = 0;
      int endIndex = 0;
      int top = 0, bottom = 0;
      boolean isReversed = false, isSwapped = false, swapMatched = false, reverseMatched = false;
               
      for(int i = 1; i < nums.length; i++){
         if(nums[i] >= nums[i-1]){
            if(isReversed){//true
               // If the reversed array does not sort correctly, return "No".
               if(nums[i] < nums[startIndex] || (startIndex > 0 && nums[i-1] < nums[startIndex-1])) return "No";
                  res = "reverse" + startIndex + "," + endIndex;
                  reverseMatched = true;
                  reverse(nums, startIndex, endIndex);
               }
               else if(swapMatched){//false
                  bottom = endIndex;
                  res = "swap" + top + "," + bottom;
                  swap(nums, top, bottom);
               }
               else if(isSwapped){//true
                  top = startIndex;
                  if(i == nums.length-1){
                     // if i is the end of the array, the bottom will be i.
                     bottom = i;
                     res = "swap" + top + "," + bottom;
                     swap(nums, top, bottom);
                  }
               }
               startIndex = i;
               continue;
            }
            if(reverseMatched || swapMatched) return "No";
            isSwapped = true;
            endIndex = i; // endIndex = 1, startIndex = 2
            if(endIndex - startIndex > 1) isReversed = true; //isReverse = true;
         }

      if(!isReversed && !isSwapped) return "yes";
      // If the last element is also in reverse order, we will need to set the result string at the end.
      if(res == "No" && isReversed){
         reverse(nums, startIndex, endIndex);
         return "reverse" + startIndex + "," + endIndex;
      }
      if(res == "No" && isSwapped){
         bottom = endIndex;
         swap(nums, top, bottom);
         return res = "swap" + top + "," + bottom;
      }

      return res;
      }

public void reverse(int[] nums, int start, int end){
   while(start < end){
      swap(nums, start++, end--);
   }
}

public void swap(int[] nums, int start, int end){
   nums[start] += nums[end];
   nums[end] = nums[start] - nums[end];
   nums[start] -= nums[end];
}