package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;

  TorpedoStore ts1;
  TorpedoStore ts2;

  @BeforeEach
  public void init(){
    ts1 = mock(TorpedoStore.class);
    ts2 = mock(TorpedoStore.class);

    this.ship = new GT4500(ts1, ts2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange

    when(ts1.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(ts1, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Empty(){
    when(ts1.isEmpty()).thenReturn(true);
    when(ts2.isEmpty()).thenReturn(true);
    //when(ts1.fire(1)).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(false, result);

    verify(ts1, times(0)).fire(1);
    verify(ts2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_PrimaryEmtpy(){
    when(ts1.isEmpty()).thenReturn(true);
    when(ts2.isEmpty()).thenReturn(false);
    when(ts2.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, result);

    verify(ts1, times(0)).fire(1);
    verify(ts2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_2Shot(){
    when(ts1.isEmpty()).thenReturn(false);
    when(ts2.isEmpty()).thenReturn(false);
    when(ts1.fire(1)).thenReturn(true);
    when(ts2.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    assertTrue(result);

    when(ts1.isEmpty()).thenReturn(true);

    result = ship.fireTorpedo(FiringMode.SINGLE);

    assertTrue(result);
    verify(ts1, times(1)).fire(1);
    verify(ts2, times(1)).fire(1);

  }

  @Test
  public void fireTorpedo_All_BothEmpty(){
    
  }



}
