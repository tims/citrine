/*
 * Copyright 2009 Last.fm
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package fm.last.citrine.notification;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fm.last.citrine.model.Notification;
import fm.last.citrine.model.Status;
import fm.last.citrine.model.TaskRun;

/**
 * Test case for the functionality in the BaseTestNotifier.
 */
public class BaseNotifierTest {

  /**
   * Simple notifier for test which does nothing.
   */
  class TestNotifier extends BaseNotifier {
    @Override
    public void notify(Notification notification, TaskRun taskRun, String taskName) {
    }
  }

  private TestNotifier notifier = new TestNotifier();
  private Notification notification = new Notification();

  @Test
  public void testNullNotification() {
    assertFalse(notifier.shouldNotify(null, Status.SUCCESS));
  }

  @Test
  public void testNotifyOnSucessTrue() {
    notification.setNotifyOnSuccess(true);
    assertTrue(notifier.shouldNotify(notification, Status.SUCCESS));
  }

  @Test
  public void testNotifyOnSucessFalse() {
    notification.setNotifyOnSuccess(false);
    assertFalse(notifier.shouldNotify(notification, Status.SUCCESS));
  }

  @Test
  public void testNotifyOnFailureTrue() {
    notification.setNotifyOnFailure(true);
    assertTrue(notifier.shouldNotify(notification, Status.FAILED));
  }

  @Test
  public void testNotifyOnFailureFalse() {
    notification.setNotifyOnFailure(false);
    assertFalse(notifier.shouldNotify(notification, Status.FAILED));
  }

  @Test
  public void testNotifyFalse() {
    notification.setNotifyOnFailure(false);
    notification.setNotifyOnSuccess(false);
    for (Status status : Status.values()) {
      assertFalse(notifier.shouldNotify(notification, status));
    }
  }

  @Test
  public void testNotifyTrue() {
    notification.setNotifyOnFailure(true);
    notification.setNotifyOnSuccess(true);
    for (Status status : Status.values()) {
      assertTrue(notifier.shouldNotify(notification, status));
    }
  }

}
