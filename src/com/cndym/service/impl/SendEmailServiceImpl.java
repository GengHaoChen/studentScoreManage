package com.cndym.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.cndym.email.MailEngine;
import com.cndym.entity.data.pick.DataInfo;
import com.cndym.entity.email.UserEmail;
import com.cndym.service.ISendEmailService;
import com.cndym.util.ConfigUtils;
import com.cndym.util.SpringUtils;
import com.cndym.util.export.Utils;

@Service("sendEmailServiceImpl")
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class SendEmailServiceImpl implements ISendEmailService {
	private Logger logger = Logger.getLogger(getClass());

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void sendEmail(List<DataInfo> list ,UserEmail email, String permissionType,String flag) {
		logger.info("query email info start");
		send(list,email,permissionType, flag);
	}

	public void send(List<DataInfo> list ,UserEmail email, String permissionType,String flag) {
		MailEngine mailEngine = (MailEngine) SpringUtils.getBean("mailEngine");
		StringBuilder  body=new StringBuilder("<table style='border-collapse: collapse;' >");
		if("fa".equals(permissionType)){
			dataInfoAll(list, body);
		}
		if("fb".equals(permissionType)){
			dataInfoChongZhi(list, body,flag);
		}
		
		if("fc".equals(permissionType)){
			dataInfoTouZhu(list, body,flag);
		}
		body.append("</table>");
		try {
			mailEngine.sendMessage(new String[]{email.getReceiveEmail()},
					ConfigUtils.getValue("MAIL.DEFAULT.FROM"), null,
					body.toString(),
					email.getTitle(), null);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private void dataInfoTouZhu(List<DataInfo> list, StringBuilder body,String flag) {
		String message="count"==flag?"����":"";
		String style="style = 'border: 1px solid black;'";
		String styleAlign="style = 'border: 1px solid black;text-align: right;'";
		body.append("<tr>                                   ");
		body.append("	<th "+style+"> ����"+message+"</th>                       ");
		body.append("	<th "+style+"> ��������"+message+"              </th>        ");
		body.append("	<th "+style+"> ������"+message+"                </th>       ");
		body.append("	<th "+style+"> ע���û���"+message+"              </th>       ");
		body.append("	<th "+style+"> Ͷע�û���"+message+"              </th>       ");
		body.append("	<th "+style+"> ��Ͷע���"+message+"              </th>       ");
		body.append("	<th "+style+"> Ͷע���û���"+message+"             </th>       ");
		body.append("	<th "+style+"> ���û�Ͷע���"+message+"            </th>       ");
		body.append("	<th "+style+"> ���û�ע��Ͷעת����"+message+"         </th>       ");
		body.append("	<th "+style+"> ȫ���û�ͶעARPU"+message+"         </th>       ");
		body.append("	<th "+style+"> ���û�ͶעARPU"+message+"          </th>       ");
		body.append("	<th "+style+"> ��������"+message+"               </th>       ");
		body.append("</tr>                                  ");
		for (DataInfo book : list) {
			body.append("<tr>");
			body.append("<td "+styleAlign+">"+book.getTimeFormat()             +"</td>");
			if("count".equals(flag)){
				body.append("<td "+styleAlign+">"+book.getSid()                    +"</td>");
			}else{
				body.append("<td "+styleAlign+">"+book.getChannelName()                    +"</td>");
			}
			
			body.append("<td "+styleAlign+">"+book.getJiHuo()                  +"</td>");
			body.append("<td "+styleAlign+">"+book.getRegisteredUserCount()    +"</td>");
			body.append("<td "+styleAlign+">"+book.getTouZhuUserCount()      +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuAllMoney())       +"</td>");
			body.append("<td "+styleAlign+">"+book.getTouZhuNewUserCount()   +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuNewUserMonery())  +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuNewUserRate())    +"%</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuAllUserArpu())    +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuNewUserArpu())	+"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getGrantsConsume())        +"</td>");
			body.append("</tr>");
		}
	}

	private void dataInfoAll(List<DataInfo> list, StringBuilder body) {
		String style="style = 'border: 1px solid black;'";
		String styleAlign="style = 'border: 1px solid black;text-align: right;'";
		body.append("<tr>                                 ");
		body.append("	<th "+style+"> ����</th>                     ");
		body.append("	<th "+style+">��������              </th>      ");
		body.append("	<th "+style+">����������               </th>      ");
		body.append("	<th "+style+">DAU����               </th>      ");
		body.append("	<th "+style+">ע���û�������             </th>      ");
		body.append("	<th "+style+">��ֵ�û�������             </th>      ");
		body.append("	<th "+style+">�ܳ�ֵ������             </th>      ");
		body.append("	<th "+style+">ȫ���û��˾���ֵ������        </th>      ");
		body.append("	<th "+style+">���û���ֵ������           </th>      ");
		body.append("	<th "+style+">���û���ֵ��������           </th>      ");
		body.append("	<th "+style+">���û��˾���ֵ������         </th>      ");
		body.append("	<th "+style+">���û���ֵת���ʻ���          </th>      ");
		body.append("	<th "+style+">Ͷע�û�������             </th>      ");
		body.append("	<th "+style+">��Ͷע������             </th>      ");
		body.append("	<th "+style+">ȫ���û�Ͷעarpu����        </th>      ");
		body.append("	<th "+style+">���û�Ͷע��������           </th>      ");
		body.append("	<th "+style+">���û�Ͷע������           </th>      ");
		body.append("	<th "+style+">����Ͷעת���ʻ���           </th>      ");
		body.append("	<th "+style+">���û�ͶעARPU����         </th>      ");
		body.append("	<th "+style+">���������              </th>      ");
		body.append("	<th "+style+">�������Ļ���              </th>      ");
		body.append("</tr>                                ");
		
		for (DataInfo book : list) {
			body.append("<tr>");
			body.append("<td "+styleAlign+">"+book.getTimeFormat()           +"</td>");
			body.append("<td "+styleAlign+">"+book.getSid()                   +"</td>");
			body.append("<td "+styleAlign+">"+book.getJiHuo()                  +"</td>");
			body.append("<td "+styleAlign+">"+book.getDau()  	 +"</td>");
			body.append("<td "+styleAlign+">"+book.getRegisteredUserCount()      +"</td>");
			body.append("<td "+styleAlign+">"+book.getChongZhiUserCount()       +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiAllMoney())   	+"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getUserAvgChongZhiMoney())  	+"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiNewUserMonery())    +"</td>");
			body.append("<td "+styleAlign+">"+book.getChongZhiNewUserCount()  	+"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiNewUserAvgMoney())		+"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiNewUserRate())                 +"%</td>");
			body.append("<td "+styleAlign+">"+book.getTouZhuUserCount()           +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuAllMoney())                   +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuAllUserArpu())                  +"</td>");
			body.append("<td "+styleAlign+">"+book.getTouZhuNewUserCount()  	 +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuNewUserMonery())      +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuNewUserRate())       +"%</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getTouZhuNewUserArpu())   	+"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getGrants())  	+"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getGrantsConsume())    +"</td>");
			body.append("</tr>");
		}
	}

	private void dataInfoChongZhi(List<DataInfo> list, StringBuilder body,String flag) {
		String message="count"==flag?"����":"";
		String style="style = 'border: 1px solid black;'";
		String styleAlign="style = 'border: 1px solid black;text-align: right;'";
		body.append("<tr>                              "); 
		body.append("	<th "+style+">���� </th>                   "); 
		body.append("	<th "+style+">��������"+message+"            </th>      "); 
		body.append("	<th "+style+">������"+message+"            </th>       "); 
		body.append("	<th "+style+">ע���û���"+message+"          </th>       "); 
		body.append("	<th "+style+">��ֵ�û���"+message+"          </th>       "); 
		body.append("	<th "+style+">�ܳ�ֵ���"+message+"          </th>       "); 
		body.append("	<th "+style+">��ֵ���û���"+message+"         </th>       "); 
		body.append("	<th "+style+">���û���ֵ���"+message+"        </th>       "); 
		body.append("	<th "+style+">���û�ע���ֵת����"+message+"     </th>       "); 
		body.append("	<th "+style+">ȫ���û��˾���ֵ���"+message+"     </th>       "); 
		body.append("	<th "+style+">���û��˾���ֵ���"+message+"      </th>       "); 
		body.append("	<th "+style+">����"+message+"             </th>       "); 
		body.append("</tr>                             "); 
		for (DataInfo book : list) {
			body.append("<tr>");
			body.append("<td "+styleAlign+">"+book.getTimeFormat()             +"</td>");
			if("count".equals(flag)){
				body.append("<td "+styleAlign+">"+book.getSid()                    +"</td>");
			}else{
				body.append("<td "+styleAlign+">"+book.getChannelName()                    +"</td>");
			}
			body.append("<td "+styleAlign+">"+book.getJiHuo()                  +"</td>");
			body.append("<td "+styleAlign+">"+book.getRegisteredUserCount()    +"</td>");
			body.append("<td "+styleAlign+">"+book.getChongZhiUserCount()      +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiAllMoney())      +"</td>");
			body.append("<td "+styleAlign+">"+book.getChongZhiNewUserCount()   +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiNewUserMonery())  +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiNewUserRate())    +"%</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getUserAvgChongZhiMoney())   +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getChongZhiNewUserAvgMoney()) +"</td>");
			body.append("<td "+styleAlign+">"+Utils.formatNumberZ(book.getGrants())                  +"</td>");
			body.append("</tr>");
		}
	}
}
