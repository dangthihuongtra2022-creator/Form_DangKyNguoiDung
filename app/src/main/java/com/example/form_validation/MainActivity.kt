package com.example.form_validation

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.form_validation.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sự kiện nút Đăng ký
        binding.btnRegister.setOnClickListener {
            handleRegister()
        }

        // Sự kiện nút Hủy -> Xóa trắng các trường nhập
        binding.btnClear.setOnClickListener {
            clearFields()
        }
    }

    private fun handleRegister() {
        val username = binding.etUsername.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()

        // 1. Validate Form
        if (username.isEmpty()) {
            showSnackbar("Tên đăng nhập không được để trống!")
            return
        }

        if (email.isEmpty()) {
            showSnackbar("Email không được để trống!")
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showSnackbar("Định dạng Email không hợp lệ!")
            return
        }

        if (phone.isEmpty()) {
            showSnackbar("Số điện thoại không được để trống!")
            return
        }

        if (phone.length < 10) {
            showSnackbar("Số điện thoại phải có ít nhất 10 chữ số!")
            return
        }

        // 2. Validate thành công -> Tạo object User và truyền sang màn hình Trang chủ
        val user = User(username, email, phone)
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("EXTRA_USER", user)
        }
        startActivity(intent)
    }

    private fun clearFields() {
        binding.etUsername.text?.clear()
        binding.etEmail.text?.clear()
        binding.etPhone.text?.clear()
        binding.etUsername.requestFocus()
        showSnackbar("Đã xóa trắng thông tin nhập!")
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}