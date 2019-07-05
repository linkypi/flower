/**
 * Copyright © 2019 同程艺龙 (zhihui.li@ly.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ly.train.flower.core.service.impl;

import com.ly.train.flower.common.annotation.Scope;
import com.ly.train.flower.common.core.service.Service;
import com.ly.train.flower.common.core.service.ServiceContext;
import com.ly.train.flower.common.util.Constant;
import com.ly.train.flower.core.service.message.Condition;

@Scope(scopeName = Constant.SCOPE_REQUEST)
public class ConditionService implements Service<Condition, Condition> {

  private String[] condition;

  public ConditionService(String config) {
    condition = config.split(",");
  }

  @Override
  public Condition process(Condition message, ServiceContext context) {
    Object o = message.getCondition();
    if (o instanceof Boolean) {
      if ((Boolean) o == true) {
        message.setCondition(condition[0]);
      } else {
        message.setCondition(condition[1]);
      }
    }
    if (o instanceof Integer && (Integer) o < condition.length) {
      message.setCondition(condition[(Integer) o]);
    }
    return message;
  }

}
