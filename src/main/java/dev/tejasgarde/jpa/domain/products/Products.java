package dev.tejasgarde.jpa.domain.products;

import dev.tejasgarde.jpa.model.ReviewsByUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products",schema = "products")
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "Products.findReviewsByUser",
        query ="select product.name , product.manufacturer, user.first_name , user.last_name,\n" +
            "address.street,address.city, review.content, review.published_date\n" +
            "from `product_service`.`products` `product`\n" +
            "INNER JOIN `review_service`.`reviews` `review` ON `review`.`product_id`=`product`.`id`\n" +
            "INNER JOIN  `user_service`.`users`  user ON `user`.`id` = `review`.`user_id`\n" +
            "INNER JOIN  `user_service`.`addresses` `address` ON `address`.`user_id`=`user`.`id`\n" +
            "where user.id = ?1",
        resultSetMapping = "reviewsByUser")
})
@SqlResultSetMappings({

    @SqlResultSetMapping(
        name = "reviewsByUser",
        classes = @ConstructorResult(
            targetClass = ReviewsByUser.class,
            columns = {
                @ColumnResult(name="name",type=String.class ),
                @ColumnResult(name="manufacturer",type=String.class ),
                @ColumnResult(name="first_name",type=String.class ),
                @ColumnResult(name="last_name",type=String.class ),
                @ColumnResult(name="street",type=String.class ),
                @ColumnResult(name="city",type=String.class ),
                @ColumnResult(name="content",type=String.class ),
                @ColumnResult(name="published_date",type= Date.class )}))
})
public class Products {
  @Id
  @Column(name = "id", nullable = false)
  private long id;
  @Column(name = "name")
  private String name;
  @Column(name = "manufacturer")
  private String manufacturer;
  @Column(name = "type")
  private String type;
}
