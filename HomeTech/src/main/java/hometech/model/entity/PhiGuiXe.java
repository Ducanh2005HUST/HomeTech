package hometech.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phi_gui_xe")
@Entity
public class PhiGuiXe {
    @Id
    private String loaiXe;

    @Column(name = "so_tien")
    private int soTien;

    @ManyToOne
    @JoinColumn(name = "khoan_thu")
    private KhoanThu khoanThu;   
}
