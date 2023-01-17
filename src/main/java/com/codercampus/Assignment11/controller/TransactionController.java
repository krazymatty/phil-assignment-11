package com.codercampus.Assignment11.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.service.TransactionService;

@Controller
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@GetMapping("/transactions")
	public String getTransactions(ModelMap model) {
		List<Transaction> allTransactions = transactionService.findAll();
		Collections.sort(allTransactions, (t1, t2) -> t1.getDate().compareTo(t2.getDate()));
		model.put("allTransactions", allTransactions);
		return "transactions";
	}

	@GetMapping("/transactions/{transactionId}")
	public String getTransactionDetail(@PathVariable Long transactionId, ModelMap model) {
		Transaction transaction = transactionService.findById(transactionId);
		model.put("transaction", transaction);
		return "transaction-detail";
	}

}
