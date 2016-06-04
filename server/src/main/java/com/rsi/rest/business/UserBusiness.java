package com.rsi.rest.business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.rsi.rest.database.HibernateUtil;
import com.rsi.rest.model.Rent;
import com.rsi.rest.model.User;

public class UserBusiness {

  public List<User> getAllUsers() {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    List<User> usersList = new ArrayList<User>();
    usersList = session.createQuery("from User").list();

    session.close();
    return usersList;
  }

  public User getUserById(int id) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    User user = new User();
    user = (User) session.get(User.class, id);

    session.close();
    return user;
  }

  public User getUserByLogin(String login) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    User user = new User();
    Criteria criteria = session.createCriteria(User.class);
    criteria.add(Restrictions.like("login", login));
    criteria.list();
    List<User> list = criteria.list();
    if (list.size() > 0) {
      user.setId(list.get(0).getId());
      user.setLastname(list.get(0).getLastname());
      user.setLogin(list.get(0).getLogin());
      user.setName(list.get(0).getName());
      user.setPassword(list.get(0).getPassword());
      session.close();
      return user;
    } else {
      return null;
    }
  }

  public void addUser(User user) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    session.save(user);
    tx.commit();
  }

  public List<Rent> getUserRents(int id) {
    List<Rent> userRentsList = new ArrayList<Rent>();
    userRentsList = getUserById(id).getRents();

    return userRentsList;
  }
}
