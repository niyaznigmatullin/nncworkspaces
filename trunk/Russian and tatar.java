﻿	class Точка {
		double икс, игрек;

		public Точка(double икс_, double игрек_) {
			икс = икс_;
			игрек = игрек_;
		}

		double длина() {
			return икс * икс + игрек * игрек;
		}
	}

	final double эпсилон = 1e-9;

	class Прямая {
		double а, бэ, цэ;

		Прямая(double а_, double бэ_, double цэ_) {
			а = а_;
			бэ = бэ_;
			цэ = цэ_;
		}

		Прямая(Точка первая, Точка вторая) {
			а = вторая.игрек - первая.игрек;
			бэ = первая.икс - вторая.икс;
			цэ = -(первая.икс * а + первая.игрек * бэ);
		}

		Точка пересечь(Прямая Л) {
			double дельта = а * Л.бэ - бэ * Л.а;
			if (Math.abs(дельта) < эпсилон) {
				return null;
			}
			return new Точка((бэ * Л.цэ - Л.бэ * цэ) / дельта, (цэ * Л.а - а
					* Л.цэ)
					/ дельта);
		}

		double расстояние_от_точки(Точка Т) {
			return модуль(а * Т.икс + бэ * Т.игрек + цэ)
					/ квадратный_корень(а * а + бэ * бэ);
		}

		private double квадратный_корень(double d) {
			return Math.sqrt(d);
		}
	}

	double модуль(double x) {
		return Math.abs(x);
	}

	long иң_зур_уртак_бүлүче(long беренче_сан, long икенче_сан) {
		if (беренче_сан == 0 || икенче_сан == 0) {
			return беренче_сан + икенче_сан;
		}
		return иң_зур_уртак_бүлүче(икенче_сан, беренче_сан % икенче_сан);
	}