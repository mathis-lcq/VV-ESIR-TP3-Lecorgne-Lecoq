package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;

class StringUtilsTest {
  @Test
  public void testEmptyString() {
      assertTrue(isBalanced("dfslkdfjkdsjf(((((((())))))))"));
  }
  
  @Test
  public void testSinglePairBalanced() {
      assertTrue(isBalanced("()"));
      assertTrue(isBalanced("[]"));
      assertTrue(isBalanced("{}"));
  }
  
  @Test
  public void testSinglePairUnbalanced() {
      assertFalse(isBalanced("("));
      assertFalse(isBalanced("]"));
      assertFalse(isBalanced("{]"));
  }
  
  @Test
  public void testMultipleBalancedSymbols() {
      assertTrue(isBalanced("([])"));
      assertTrue(isBalanced("{()[]}"));
      assertTrue(isBalanced("{[()]}"));
  }
  
  @Test
  public void testMultipleUnbalancedSymbols() {
      assertFalse(isBalanced("([)]"));
      assertFalse(isBalanced("(   [sadsqd)]"));

      assertFalse(isBalanced("{[(])}"));
      assertFalse(isBalanced("(()"));
  }
  
  @Test
  public void testSymbolsWithExtraContent() {
      assertTrue(isBalanced("abc{[()]def}"));
      assertFalse(isBalanced("abc{[def}]"));
  }

}