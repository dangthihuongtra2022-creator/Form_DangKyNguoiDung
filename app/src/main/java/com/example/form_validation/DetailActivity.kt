package com.example.form_validation

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.form_validation.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận đối tượng Serializable User từ MainActivity
        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("EXTRA_USER", User::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("EXTRA_USER") as? User
        }

        // Hiển thị thông tin lên giao diện nếu object user không null
        user?.let {
            val info = """
                THÔNG TIN NGƯỜI DÙNG ĐĂNG KÝ
                -----------------------------------
                - Tên đăng nhập: ${it.username}
                - Email: ${it.email}
                - Số điện thoại: ${it.phone}
            """.trimIndent()

            // Đảm bảo trong activity_detail.xml của bạn có một TextView tên là tvResultInfo
            binding.tvResultInfo.text = info
        }
    }
}